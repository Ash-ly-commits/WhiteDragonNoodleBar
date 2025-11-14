package com.pluralsight.util;

import com.pluralsight.items.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {
    private static final String RECEIPT_DIR = "src/main/resources/receipts/";

    private String generateTimestamp() {
        LocalDateTime rightNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return rightNow.format(formatter);
    }

    public void saveReceipt(Order order) {
        File directory = new File(RECEIPT_DIR);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.err.println("Could not create receipts directory: " + directory.getAbsolutePath());
            }
        }
        String filePath = RECEIPT_DIR + generateTimestamp() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(order.getOrderSummary());
        } catch (IOException e) {
            System.err.println("Error writing receipt: " + e.getMessage());
        }
    }
}
