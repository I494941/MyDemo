package com.example.a94941.mydemo;

import com.example.a94941.mydemo.activitys.JUnit4Activity.Calculator;

import org.junit.Assert;
import org.junit.Test;

/**
 * @创建者 94941
 * @创建时间 2018/2/22
 * @描述 ${TODO}
 */
public class CalculatorTest {

    @Test
    public void testAdd() throws Exception {
        Calculator calculator = new Calculator();
        int sum = calculator.add(1, 2);
        Assert.assertEquals(3, sum);
    }
}