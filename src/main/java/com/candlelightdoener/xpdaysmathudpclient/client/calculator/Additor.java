package com.candlelightdoener.xpdaysmathudpclient.client.calculator;

import java.util.List;

/**
 * Seb 18.11.12, 20:51
 */
public class Additor implements Calculator {

    public int calculate(List<Integer> values) {
        int result = 0;
        for (Integer value : values) {
            result += value;
        }
        return result;
    }
}
