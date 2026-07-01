package com.totalbeginner.mathsCalculator.service.numberTheory;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.totalbeginner.mathsCalculator.dto.numberTheory.PrimeFactorizationResult;

@Service
public class PrimeFactorizationService {

    public PrimeFactorizationResult buildPrimeFactorizationResult(int number) {

        PrimeFactorizationResult result = new PrimeFactorizationResult();

        result.setOriginalNumber(number);
        result.setCurrentNumber(number);
        result.setCurrentStep(0);
        result.setCurrentFactor(0);
        result.setCompleted(false);
        result.setFactorsDisplay("");

        // Number entered is already prime
        if (number > 1 && isPrime(number)) {

            List<Integer> factors = new ArrayList<>();
            factors.add(number);

            result.setFactors(factors);
            result.setFactorsDisplay(buildDisplay(factors));
            result.setFinalFactorizationDisplay(number + " = " + number);
            result.setFeedbackMessage(number + " is already a prime number.");
            result.setCurrentNumber(1);
            result.setCompleted(true);
        }

        return result;
    }

    public PrimeFactorizationResult checkPrimeFactor(
            PrimeFactorizationResult result,
            int attemptedPrime,
            String factors) {

        List<Integer> factorList = parseFactors(factors);

        int currentNumber = result.getCurrentNumber();

        result.setCurrentFactor(attemptedPrime);
        result.setRemainder(currentNumber % attemptedPrime);
        result.setQuotient(currentNumber / attemptedPrime);

        if (currentNumber % attemptedPrime == 0) {

            int newCurrentNumber = currentNumber / attemptedPrime;

            factorList.add(attemptedPrime);

            result.setCurrentNumber(newCurrentNumber);
            result.setSuccessfulDivision(true);

            /*
             * Remaining value is prime.
             */
            if (newCurrentNumber > 1 && isPrime(newCurrentNumber)) {

                factorList.add(newCurrentNumber);

                result.setCurrentNumber(1);
                result.setCompleted(true);

                result.setFeedbackMessage(
                        currentNumber + " ÷ " + attemptedPrime + " = "
                                + newCurrentNumber
                                + ". The remaining value, "
                                + newCurrentNumber
                                + ", is prime. Prime factorization complete."
                );

                result.setFactors(factorList);
                result.setFactorsDisplay(buildDisplay(factorList));
                result.setFinalFactorizationDisplay(
                        result.getOriginalNumber() + " = " + buildDisplay(factorList)
                );

                return result;
            }

            result.setFeedbackMessage(
                    currentNumber + " ÷ " + attemptedPrime + " = "
                            + newCurrentNumber
                            + ". Correct! " + attemptedPrime
                            + " is a prime factor."
            );

            if (newCurrentNumber == 1) {

                result.setCompleted(true);

                result.setFinalFactorizationDisplay(
                        result.getOriginalNumber() + " = "
                                + buildDisplay(factorList)
                );
            }

        } else {

            result.setSuccessfulDivision(false);

            result.setFeedbackMessage(
                    currentNumber + " is not divisible by "
                            + attemptedPrime
                            + ". Try another prime."
            );
        }

        result.setFactors(factorList);
        result.setFactorsDisplay(buildDisplay(factorList));

        return result;
    }

    public List<Integer> buildPrimeButtons() {

        return List.of(
                2, 3, 5, 7, 11,
                13, 17, 19, 23, 29,
                31, 37, 41, 43, 47,
                53, 59, 61, 67, 71,
                73, 79, 83, 89, 97
        );
    }

    private List<Integer> parseFactors(String factors) {

        List<Integer> factorList = new ArrayList<>();

        if (factors == null || factors.isBlank()) {
            return factorList;
        }

        String normalisedFactors = factors.replace(" × ", ",");

        String[] parts = normalisedFactors.split(",");

        for (String part : parts) {

            String cleanedPart = part.trim();

            if (!cleanedPart.isBlank()) {
                factorList.add(Integer.parseInt(cleanedPart));
            }
        }

        return factorList;
    }

    private String buildDisplay(List<Integer> factors) {

        if (factors == null || factors.isEmpty()) {
            return "";
        }

        return factors.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" × "));
    }

    private boolean isPrime(int number) {

        if (number < 2) {
            return false;
        }

        for (int i = 2; i * i <= number; i++) {

            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}