package com.tobidaada.primetablechallenge;

import com.tobidaada.primetablechallenge.utils.PrimeNumberGeneratorUtils;

import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PrimeNumberUnitTest {

    @Test
    public void primeNumberGeneratorReturnsRequiredLength() {
        Random random = new Random();
        int length = random.nextInt(100);
        List<Integer> primeNumbers = PrimeNumberGeneratorUtils.generatePrimeNumbers(length);

        assertEquals(primeNumbers.size(), length);
    }
}
