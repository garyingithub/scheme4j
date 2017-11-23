package cn.galium.scheme4j.type;

import cn.galium.scheme4j.Environment;
import cn.galium.scheme4j.type.properties.Calculable;

public class Number implements Calculable<Number>, Comparable<Number> {

    private Object number;

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

    public Object evaluate(Object x, Environment environment) {
        return x;
    }

    @FunctionalInterface
    interface Calculator<T> {
        T calculate(T a, T b);
    }

    private Number calculate(Number a, Calculator<Double> doubleCalculator, Calculator<Long> longCalculator) {
        if (number instanceof Double) {
            Double b = a.number instanceof Double ? (Double) a.number : ((Long) a.number).doubleValue();
            return new Number(doubleCalculator.calculate((Double) number, b));
        } else {
            if (a.number instanceof Double) {
                return new Number(doubleCalculator.calculate((((Long) number).doubleValue()), (Double) a.number));
            } else {
                return new Number(longCalculator.calculate((Long) number, (Long) a.number));
            }
        }
    }


    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(Number a) {
        if (number instanceof Double) {
            if (!(a.number instanceof Double)) {
                throw new IllegalArgumentException();
            }
            return Double.compare((Double) number, (Double) (a.number));
        } else {
            if (!(a.number instanceof Long)) {
                throw new IllegalArgumentException();
            }
            return Long.compare((Long) number, (Long) (a.number));
        }
    }
}
