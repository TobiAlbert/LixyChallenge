package com.tobidaada.primetablechallenge;

import com.tobidaada.primetablechallenge.utils.PrimeNumberGeneratorUtils;

import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PrimeNumberUnitTest {

    @Test
    public void primeNumberGeneratorReturnsRequiredLength() {

        // generate a random integer to serve a input
        // ensure that the size of list returned is equal
        // to the random number generated.

        Random random = new Random();
        int length = random.nextInt(100);
        List<Integer> primeNumbers = PrimeNumberGeneratorUtils.generatePrimeNumbers(length);

        assertEquals(primeNumbers.size(), length);
    }
}
