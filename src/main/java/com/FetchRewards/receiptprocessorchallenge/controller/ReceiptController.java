package com.FetchRewards.receiptprocessorchallenge.controller;

import com.FetchRewards.receiptprocessorchallenge.model.Receipt;
import com.FetchRewards.receiptprocessorchallenge.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    //use HashMap to store receipt in memory
    private final Map<String, Receipt> receiptMap = new HashMap<>();
    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    /**
     * Path: /receipts/process
     * Method: POST
     * Payload: Receipt JSON
     * Response: JSON containing an id for the receipt.
     * Takes in a JSON receipt and returns a JSON object with an ID generated.
     * @param receipt
     * @return id
     */
    @PostMapping("/process")
    public ResponseEntity<Map<String, String>> processReceipt(@RequestBody Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receipt.setPoints(receiptService.calculatePoints(receipt));
        receiptMap.put(id, receipt);
        Map<String, String> response = new HashMap<>();
        response.put("id", id);
        return ResponseEntity.ok(response);
    }

    /**
     * Path: /receipts/{id}/points
     * Method: GET
     * Response: A JSON object containing the number of points awarded.
     * A simple Getter endpoint that looks up the receipt by the ID and returns an object specifying the points awarded
     * @param id
     * @return points
     */
    @GetMapping("/{id}/points")
    public ResponseEntity<Map<String, Integer>> getPoints(@PathVariable String id) {
        Receipt receipt = receiptMap.get(id);
        if (receipt == null) {
            return ResponseEntity.notFound().build();
        }
        Map<String, Integer> response = new HashMap<>();
        response.put("points", receipt.getPoints());
        return ResponseEntity.ok(response);
    }

}
