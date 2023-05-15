package com.FetchRewards.receiptprocessorchallenge.service;

import com.FetchRewards.receiptprocessorchallenge.model.Item;
import com.FetchRewards.receiptprocessorchallenge.model.Receipt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
public class ReceiptServiceTest {

    private ReceiptService receiptService = new ReceiptService();

    @Test
    public void testCalculatePoints() {
        Receipt receipt = new Receipt();
        receipt.setRetailer("M&M Corner Market");
        receipt.setPurchaseDate(LocalDate.of(2022, 3, 20));
        receipt.setPurchaseTime(LocalTime.of(14, 33));
        List<Item> items = Arrays.asList(
                new Item("Gatorade", BigDecimal.valueOf(2.25)),
                new Item("Gatorade", BigDecimal.valueOf(2.25)),
                new Item("Gatorade", BigDecimal.valueOf(2.25)),
                new Item("Gatorade", BigDecimal.valueOf(2.25))
        );
        receipt.setItems(items);
        receipt.setTotal(BigDecimal.valueOf(9.00));

        int points = receiptService.calculatePoints(receipt);

        assertEquals(109, points);
    }
}