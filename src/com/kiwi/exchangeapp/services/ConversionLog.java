package com.kiwi.exchangeapp.services;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConversionLog {
    public static void log(String baseCurrency,
                           String conversionCurrency,
                           double amount,
                           double convertedAmount) {
        try (FileWriter writer = new FileWriter("conversionlog.txt", true)) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = now.format(formatter);

            String logEntry = String.format("%s: Converted %.2f %s to %.2f %s%n", timestamp, amount, baseCurrency, convertedAmount, conversionCurrency);
            writer.write(logEntry);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}