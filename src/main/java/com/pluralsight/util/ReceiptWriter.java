package com.pluralsight.util;

import com.pluralsight.items.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {
    private String generateTimestamp() {
        LocalDateTime rightNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return rightNow.format(formatter);
    }

    public void saveReceipt(Order order) {
        String txtFileName = generateTimestamp() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtFileName))) {
            writer.write(order.getOrderSummary());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
