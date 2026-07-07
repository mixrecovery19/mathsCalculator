package com.totalbeginner.mathsCalculator.service.numberTheory;

import com.totalbeginner.mathsCalculator.dto.numberTheory.RsaEncryptionResult;
import org.springframework.stereotype.Service;

@Service
public class RsaEncryptionService {

    public RsaEncryptionResult buildResult(int p1, int p2, int e, Integer message, Integer ciphertext) {
        RsaEncryptionResult result = new RsaEncryptionResult();

        result.setP1(p1);
        result.setP2(p2);
        result.setE(e);
        result.setMessage(message);
        result.setCiphertext(ciphertext);
        result.setHasValues(true);

        if (!isPrime(p1) || !isPrime(p2)) {
            result.setValidPrimes(false);
            result.setErrorMessage("Both p₁ and p₂ must be prime numbers.");
            return result;
        }

        result.setValidPrimes(true);

        int n = p1 * p2;
        int phi = (p1 - 1) * (p2 - 1);

        result.setN(n);
        result.setPhi(phi);

        if (gcd(e, phi) != 1) {
            result.setValidE(false);
            result.setErrorMessage("The value of e must be coprime with φ.");
            return result;
        }

        result.setValidE(true);

        int d = calculateD(e, phi);
        result.setD(d);

        if (message != null) {
            result.setCiphertext(modularPower(message, e, n));
        }

        if (ciphertext != null) {
            result.setDecryptedMessage(modularPower(ciphertext, d, n));
        }

        return result;
    }
    private int calculateD(int e, int phi) {
        for (int x = 1; x < phi * e; x++) {
            if ((phi * x + 1) % e == 0) {
                return (phi * x + 1) / e;
            }
        }

        throw new IllegalArgumentException("Unable to calculate d.");
    }

    private int modularPower(int base, int exponent, int modulus) {
        int result = 1;

        for (int i = 0; i < exponent; i++) {
            result = (result * base) % modulus;
        }

        return result;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return Math.abs(a);
    }

    private boolean isPrime(int value) {
        if (value < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(value); i++) {
            if (value % i == 0) {
                return false;
            }
        }

        return true;
    }
}