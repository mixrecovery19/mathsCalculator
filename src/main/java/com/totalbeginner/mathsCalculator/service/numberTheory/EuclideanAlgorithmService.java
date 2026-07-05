package com.totalbeginner.mathsCalculator.service.numberTheory;

import org.springframework.stereotype.Service;

import com.totalbeginner.mathsCalculator.dto.numberTheory.EuclideanAlgorithmResult;
import com.totalbeginner.mathsCalculator.dto.numberTheory.EuclideanAlgorithmStep;

@Service
public class EuclideanAlgorithmService {
    public void buildEuclideanWalkthrough(EuclideanAlgorithmResult result) {

        result.getSteps().clear();

        int dividend = Math.max(result.getFirstNumber(), result.getSecondNumber());
        int divisor = Math.min(result.getFirstNumber(), result.getSecondNumber());

        while (divisor != 0) {
            int quotient = dividend / divisor;
            int remainder = dividend % divisor;

            result.getSteps().add(
                new EuclideanAlgorithmStep(
                    dividend,
                    divisor,
                    quotient,
                    remainder));
            dividend = divisor;
            divisor = remainder;
        }
        result.setGreatestCommonDivisor(dividend);
        result.setCompleted(true);
        result.setHasResult(true);
        result.setMaximumEuclideanStep(result.getSteps().size());
    }
}