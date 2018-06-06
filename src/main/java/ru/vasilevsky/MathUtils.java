package ru.vasilevsky;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MathUtils {
    public static Integer[] calculateReachMatrix(Integer[] matrix) {
        Integer[] G = new Integer[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int x = matrix[j];
                for (int k = 0; k < matrix.length; k++) {
                    if ((matrix[j] & (1 << i)) > 0 && (matrix[i] & (1 << k)) > 0) x |= 1 << k;
                }
                G[j] = x;
            }
        }

        return G;
    }
}
