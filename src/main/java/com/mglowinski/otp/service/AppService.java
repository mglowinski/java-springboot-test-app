package com.mglowinski.otp.service;

import org.springframework.stereotype.Service;

@Service
public class AppService {

    public int getSumOfSquares(Integer n) {
        if (n == null) {
            n = 10;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (i * i);
        }
        return sum;
    }

    public int[] getFibonacciSeriesRecursively(Integer n) {
        if (n == null) {
            n = 10;
        }

        int[] results = new int[n];
        for (int i = 0; i < n; i++) {
            results[i] = fib(i);
        }
        return results;
    }

    public int[] getFibonacciSeriesIteratively(Integer n) {
        if (n == null) {
            n = 10;
        }

        int[] results = new int[n];
        int x = 0;
        int y = 1;

        for (int i = 0; i < n; i++) {
            int z = x + y;
            results[i] = z;
            x = y;
            y = z;
        }

        return results;
    }

    private int fib(int n) {
        if (n > 1) {
            return fib(n - 1) + fib(n - 2);
        } else {
            return n;
        }
    }
}
