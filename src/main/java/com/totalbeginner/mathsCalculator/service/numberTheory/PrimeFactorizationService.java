package com.totalbeginner.mathsCalculator.service.numberTheory;

import org.springframework.stereotype.Service;

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

        return result;
    }   

}