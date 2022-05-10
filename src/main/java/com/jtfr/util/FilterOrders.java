package com.jtfr.util;

import org.springframework.core.Ordered;

/**
 * 数值越小，filter 加载越前
 */
public class FilterOrders {

    private FilterOrders(){}

    public static final int HIGHEST = Ordered.HIGHEST_PRECEDENCE;

    public static final int ZERO = 2;
    public static final int ONE = 4;
    public static final int TWO = 8;
    public static final int THREE = 16;
    public static final int FOUR = 32;
    public static final int FIVE = 64;
    public static final int SIX = 128;
    public static final int SEVEN = 256;
    public static final int EIGHT = 512;
    public static final int NINE = 1024;

    public static final int LOWEST = Ordered.LOWEST_PRECEDENCE;
}
