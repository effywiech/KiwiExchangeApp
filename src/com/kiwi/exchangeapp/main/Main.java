package com.kiwi.exchangeapp.main;

import com.kiwi.exchangeapp.services.ConsultAPI;
import com.kiwi.exchangeapp.services.ConsultAPI.Currencies;
import com.kiwi.exchangeapp.services.ConversionLog;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Declaring variables
        int option = 0;
        String baseCurrency = "";
        String conversionCurrency = "";

        // Menu
        String menu = """
                ******************************
                
                Welcome to Kiwi's Currency Converter
                
                1) USD =>> COP
                2) COP =>> USD
                3) USD =>> EUR
                4) EUR =>> USD
                5) USD =>> MXN
                6) MXN =>> COP
                7) EXIT
                
                ******************************
                
                Please choose a valid option
                
                """;

        // Making a scanner so we can get user's choice
        Scanner numberInput = new Scanner(System.in);

        while (true) {
            System.out.println(menu);
            try {
                option = numberInput.nextInt();

                // If user selects options 1 to 6 we need to ask for an amount to convert
                if (option >= 1 && option <= 6) {
                    System.out.println("Please enter the amount you want to convert:");
                    double amount = numberInput.nextDouble();

                    // Perform the conversion based on the selected option
                    switch (option) {
                        case 1 -> {
                            baseCurrency = "USD";
                            conversionCurrency = "COP";
                        }
                        case 2 -> {
                            baseCurrency = "COP";
                            conversionCurrency = "USD";
                        }
                        case 3 -> {
                            baseCurrency = "USD";
                            conversionCurrency = "EUR";
                        }
                        case 4 -> {
                            baseCurrency = "EUR";
                            conversionCurrency = "USD";
                        }
                        case 5 -> {
                            baseCurrency = "USD";
                            conversionCurrency = "MXN";
                        }
                        case 6 -> {
                            baseCurrency = "MXN";
                            conversionCurrency = "COP";
                        }
                    }

                    // Perform the conversion and handle any potential exceptions
                    try {
                        Currencies conversionResult = ConsultAPI.convertCurrency(baseCurrency, conversionCurrency, amount);
                        if ("success".equals(conversionResult.getResult())) {
                            double convertedAmount = conversionResult.getConversionResult();
                            System.out.println("Converted amount: " + convertedAmount + " " + conversionCurrency);
                            //Log conversion
                            ConversionLog.log(baseCurrency, conversionCurrency, amount, convertedAmount);
                        } else {
                            System.out.println("Conversion failed. Please try again.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error during conversion: " + e.getMessage());
                    }

                } else if (option == 7) {
                    // Exit option
                    System.out.println("Exiting the program. Thank you for using our service!");
                    break;
                } else {
                    System.out.println("Invalid option. Please select a number between 1 and 7.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                numberInput.next();
            }
        }

        // Close the scanner
        numberInput.close();
    }
}