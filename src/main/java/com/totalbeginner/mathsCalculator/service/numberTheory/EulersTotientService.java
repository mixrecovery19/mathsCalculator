package com.totalbeginner.mathsCalculator.service.numberTheory;

import org.springframework.stereotype.Service;

import com.totalbeginner.mathsCalculator.dto.numberTheory.EulerTotientFactor;
import com.totalbeginner.mathsCalculator.dto.numberTheory.EulersTotientResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class EulersTotientService {
    public List<Integer> getInitialValueDistinctPrimeFactors(int number) {
        List<Integer>distinctPrimeFactors = new ArrayList<>();
        int value = number;
        for (int divisor = 2; divisor * divisor <= value; divisor++) {
            if (value % divisor == 0) {
                distinctPrimeFactors.add(divisor);
                while (value % divisor == 0) {
                    value /= divisor;
                }
            }
        }
        if (value > 1) {
            distinctPrimeFactors.add(value);
        }
        return distinctPrimeFactors;
    }

    public List<EulerTotientFactor> buildEulerFactors(int number) {
        List<Integer> distinctPrimes =
            getInitialValueDistinctPrimeFactors(number);

        List<EulerTotientFactor> factors = new ArrayList<>();
        for (Integer prime : distinctPrimes) {
            factors.add(new EulerTotientFactor(prime));
        }

    return factors;
    }

    public void simplifyEulerFactors(List<EulerTotientFactor> factors) {
        for (EulerTotientFactor factor : factors) {
            int numerator = factor.getPrime() - 1;
            int denominator = factor.getPrime();
            factor.setNumerator(numerator);
            factor.setDenominator(denominator);

            factor.setDisplayValue("(" + numerator + "/" + denominator + ")");
        }
    }
    public int multiplyByFactor(int value, EulerTotientFactor factor) {
        return value * factor.getNumerator() / factor.getDenominator();
    }
    public int calculateCurrentValue(int number, List<EulerTotientFactor> factors, int currentStep) {
        int currentValue = number;
        int factorsToApply = Math.max(0, currentStep - 1);
        for (int i = 0;
            i < factorsToApply && i < factors.size();
            i++) {
            currentValue =
                    multiplyByFactor(currentValue, factors.get(i));
        }
        return currentValue;
    }
        public void buildSectionTwoWalkthroughState(
        EulersTotientResult result) {

    List<EulerTotientFactor> factors = result.getFactors();

    int activeFactorIndex =
            result.getEulersSectionTwoCurrentStep() - 2;

    if (activeFactorIndex < 0 || activeFactorIndex >= factors.size()) {
        return;
    }

    result.setActiveFactorIndex(activeFactorIndex);

    int previousValue = result.getNumber();

    for (int i = 0; i < activeFactorIndex; i++) {
        previousValue =
                multiplyByFactor(previousValue, factors.get(i));
    }

    result.setPreviousValue(previousValue);

    result.setCurrentValue(
            multiplyByFactor(
                    previousValue,
                    factors.get(activeFactorIndex)
            )
    );
}
}
