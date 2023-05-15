package com.FetchRewards.receiptprocessorchallenge.service;

import com.FetchRewards.receiptprocessorchallenge.model.Item;
import com.FetchRewards.receiptprocessorchallenge.model.Receipt;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ReceiptService {

    public int calculatePoints(Receipt receipt) {
        int points = 0;

        // One point for every alphanumeric character in the retailer name.
        int retailerPoints = receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();
        points += retailerPoints;
        System.out.println(retailerPoints + " points - retailer name has " + retailerPoints + " alphanumeric characters");


        // 50 points if the total is a round dollar amount with no cents.
        BigDecimal total = receipt.getTotal();
        if (total.stripTrailingZeros().scale() <= 0) {
            points += 50;
            System.out.println("50 points - total is a round dollar amount");
        }

        // 25 points if the total is a multiple of 0.25.
        if (total.remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0) {
            points += 25;
            System.out.println("25 points - total is a multiple of 0.25");
        }


        // 5 points for every two items on the receipt.
        int itemPairs = receipt.getItems().size() / 2;
        points += itemPairs * 5;
        System.out.println(itemPairs * 5 + " points - " + receipt.getItems().size() + " items (" + itemPairs + " pairs @ 5 points each)");


        // If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
        int itemDescriptionPoints = 0;
        for (Item item : receipt.getItems()) {
            if (item.getShortDescription().trim().length() % 3 == 0) {
                BigDecimal price = item.getPrice();
                int itemPoints = price.multiply(new BigDecimal("0.2")).setScale(0, RoundingMode.UP).intValue();
                itemDescriptionPoints += Math.max(itemPoints, 1);
            }
        }
        points += itemDescriptionPoints;
        if (itemDescriptionPoints>0) {
            System.out.println(itemDescriptionPoints + " points - from item descriptions");
        }

        // 6 points if the day in the purchase date is odd.
        if (receipt.getPurchaseDate().getDayOfMonth() % 2 == 1) {
            points += 6;
            System.out.println("6 points - purchase day is odd");
        }

        //10 points if the time of purchase is after 2:00pm and before 4:00pm.
        if (receipt.getPurchaseTime().getHour() >= 14 && receipt.getPurchaseTime().getHour() < 16) {
            points += 10;
            System.out.println("10 points - purchase time is between 2:00pm and 4:00pm");
        }

        System.out.println("Total Points: " + points);
        return points;
    }
}
