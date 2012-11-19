package com.candlelightdoener.xpdaysmathudpclient.client.calculator;

import java.util.List;

/**
 * Seb 18.11.12, 20:56
 */
public class Subtractor implements Calculator {

    public int calculate(List<Integer> values) {
        if(values.size() == 0)
            return 0;

        int result = values.get(0) * 2;
        for (Integer value : values) {
            result -= value;
        }
        return result;
    }
}
