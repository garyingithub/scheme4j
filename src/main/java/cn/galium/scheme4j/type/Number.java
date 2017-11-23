package cn.galium.scheme4j.type;

import cn.galium.scheme4j.type.properties.Calculable;

/**
 * @author gary
 */
public class Number extends java.lang.Number implements Calculable<Number>, Comparable<Number> {

    private java.lang.Number number;

    public Number(String symbol) {
        try {
            number = Long.parseLong(symbol);
        } catch (NumberFormatException e) {
            number = Double.parseDouble(symbol);
        }
    }

    private Number(Double number) {
        this.number = number;
    }

    private Number(Long number) {
        this.number = number;
    }


    @Override
    public Number add(Number a) {
        return calculate(a, (a1, b) -> a1 + b, (a12, b) -> a12 + b);

    }

    @Override
    public Number minus(Number a) {
        return calculate(a, (a1, b) -> a1 - b, (a12, b) -> a12 - b);

    }

    @Override
    public Number multiply(Number a) {
        return calculate(a, (a1, b) -> a1 * b, (a12, b) -> a12 * b);

    }

    @Override
    public Number divide(Number a) {
        return calculate(a, (a1, b) -> a1 / b, (a12, b) -> a12 / b);
    }

    @Override
    public Number mod(Number a) {
        return calculate(a, (a1, b) -> a1 % b, (a12, b) -> a12 % b);

    }

    @Override
    public int intValue() {
        return number.intValue();
    }

    @Override
    public long longValue() {
        return number.longValue();
    }

    @Override
    public float floatValue() {
        return number.floatValue();
    }

    @Override
    public double doubleValue() {
        return number.doubleValue();
    }

    @FunctionalInterface
    interface Calculator<T> {
        T calculate(T a, T b);
    }

    private Number calculate(Number a, Calculator<Double> doubleCalculator, Calculator<Long> longCalculator) {

        if (number instanceof Double || a.number instanceof Double) {
            return new Number(doubleCalculator.calculate(doubleValue(), a.doubleValue()));
        } else {
            return new Number(longCalculator.calculate(longValue(), a.longValue()));
        }
    }


    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(Number a) {
        return Double.compare((number).doubleValue(), (a.number).floatValue());
    }
}
