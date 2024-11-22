package com.currencyconverter;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExchangeRateService service = new ExchangeRateService();
        try{
            CurrencyResponse rates = service.getExchangeRates();

            System.out.println("Tasas de cambios basadas en: " + rates.getBase_code());
            System.out.println("Monedas disponibles: " + rates.getConversion_rates().keySet());

            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingresa la moneda de origen (ej. USD): ");
            String sourceCurrency = scanner.nextLine().toUpperCase();

            System.out.println("Ingresa la moneda de destino (ej. EUR): ");
            String targetCurrency = scanner.nextLine().toUpperCase();

            System.out.println("Ingresa el monto a convertir: ");
            double amount = scanner.nextDouble();

            Double sourceRate = rates.getConversion_rates().get(sourceCurrency);
            Double targetRate = rates.getConversion_rates().get(targetCurrency);

            if (sourceRate != null && targetRate != null){
                double convertedAmount = (amount / sourceRate) * targetRate;
                System.out.printf("%.2f %s son equivalentes a %.2f %s%n", amount, sourceCurrency, convertedAmount, targetCurrency);
            } else {
                System.out.println("Error: Una de las monedas no está disponible.");
            }
        } catch (IOException | InterruptedException e){
            System.out.println("Hubo un error al conectar con la API: " + e.getMessage());
        }
    }
}
