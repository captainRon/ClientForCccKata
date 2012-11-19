package com.candlelightdoener.xpdaysmathudpclient.client;

import com.candlelightdoener.xpdaysmathudpclient.client.calculator.Additor;
import com.candlelightdoener.xpdaysmathudpclient.client.calculator.Calculator;
import com.candlelightdoener.xpdaysmathudpclient.client.calculator.Multiplicator;
import com.candlelightdoener.xpdaysmathudpclient.client.calculator.Subtractor;

import java.util.List;

/**
 * Changes when new function appears.
 */
enum Function {
    ADD (new Additor()),
    MULTIPLY (new Multiplicator()),
    SUBTRACT (new Subtractor()),
    __SHUTDOWN__ (null);  //TODO this is not so nice..seems like it doesn't belong here..

    private Calculator calculator;

    Function(Calculator calculator) {
        this.calculator = calculator;
    }

    public int calculate(List<Integer> values) {
        return calculator.calculate(values);
    }
}
