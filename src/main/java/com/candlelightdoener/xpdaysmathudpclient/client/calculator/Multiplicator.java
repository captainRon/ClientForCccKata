package com.candlelightdoener.xpdaysmathudpclient.client.calculator;

import java.util.List;

/**
 * Seb 18.11.12, 20:56
 */
public class Multiplicator implements Calculator {

    public int calculate(List<Integer> values) {
        int result = 1;
        for (Integer value : values) {
            result *= value;
        }
        return result;
    }
}
