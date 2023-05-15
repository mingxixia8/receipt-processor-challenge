package com.FetchRewards.receiptprocessorchallenge.controller;

import com.FetchRewards.receiptprocessorchallenge.model.Item;
import com.FetchRewards.receiptprocessorchallenge.model.Receipt;
import com.FetchRewards.receiptprocessorchallenge.service.ReceiptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ReceiptControllerTest {
    @InjectMocks
    ReceiptController receiptController;

    @Mock
    ReceiptService receiptService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        receiptController = new ReceiptController(receiptService);

    }

    @Test
    public void testProcessReceipt() {
        // Prepare the input receipt
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

        ResponseEntity<Map<String, String>> response = receiptController.processReceipt(receipt);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        String id = response.getBody().get("id");
        assertNotNull(UUID.fromString(id)); // check if id is a valid UUID
    }


    @Test
    public void testGetPoints_ReceiptExist() {
        Receipt testReceipt = new Receipt(); //initialize your test receipt here
        testReceipt.setPoints(109);

        //get the id from the response of processReceipt
        ResponseEntity<Map<String, String>> processResponse = receiptController.processReceipt(testReceipt);
        String id = processResponse.getBody().get("id");

        ResponseEntity<Map<String, Integer>> response = receiptController.getPoints(id);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());


    }


}

