package com.totalbeginner.mathsCalculator.service;

import org.springframework.stereotype.Service;
import java.util.Map;
@Service
public class MatrixRequestParser {
    public double[][] buildMatrix(int size, Map<String, String> params, String prefix ) {
        double[][] matrix = new double[size][size];
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    String key = prefix + row + "_" + col;
                    String value = params.get(key);

                    matrix[row][col] =
                            value == null
                            || value.isBlank()
                            ? 0
                            : Double.parseDouble(
                                    value
                            );
                }
            }
            return matrix;
        }
}