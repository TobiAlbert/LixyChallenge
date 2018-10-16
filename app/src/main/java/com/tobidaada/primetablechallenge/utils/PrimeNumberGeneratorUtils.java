package com.tobidaada.primetablechallenge.utils;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberGeneratorUtils {

    public static List<Integer> generatePrimeNumbers(int length) {
        List<Integer> primeNumbers = new ArrayList<>();

        int counter = 0;
        int number = 2;

        while(counter < length) {
            if (isPrimeNumber(number)) {
                primeNumbers.add(number);
                counter++;
            }

            number++;
        }

        return primeNumbers;
    }

    private static boolean isPrimeNumber(int number) {
        int i = 2;
        while(i < number) {
            if (number % i == 0) {
                return false;
            }

            i++;
        }

        return true;
    }
}
