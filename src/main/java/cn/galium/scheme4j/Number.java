package cn.galium.scheme4j;

public class Number implements Type<Number> {

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
        if(number instanceof Double) {
            if(!(a.number instanceof Double)) {
                throw new IllegalArgumentException();
            }
            return new Number((Double)number + (Double)a.number);
        } else {
            if(!(a.number instanceof Long)) {
                throw new IllegalArgumentException();
            }
            return new Number((Long) number + (Long) a.number);
        }
    }

    @Override
    public Number minus(Number a) {
        if(number instanceof Double) {
            if(!(a.number instanceof Double)) {
                throw new IllegalArgumentException();
            }
            return new Number((Double)number - (Double)a.number);
        } else {
            if(!(a.number instanceof Long)) {
                throw new IllegalArgumentException();
            }
            return new Number((Long) number - (Long) a.number);
        }
    }

    @Override
    public Number multiply(Number a) {
        if(number instanceof Double) {
            if(!(a.number instanceof Double)) {
                throw new IllegalArgumentException();
            }
            return new Number((Double)number * (Double)a.number);
        } else {
            if(!(a.number instanceof Long)) {
                throw new IllegalArgumentException();
            }
            return new Number((Long) number * (Long) a.number);
        }
    }

    @Override
    public Number divide(Number a) {
        if(number instanceof Double) {
            if(!(a.number instanceof Double)) {
                throw new IllegalArgumentException();
            }
            return new Number((Double)number / (Double)a.number);
        } else {
            if(!(a.number instanceof Long)) {
                throw new IllegalArgumentException();
            }
            return new Number((Long) number / (Long) a.number);
        }
    }

    public Boolean lt(Number a) {
        if(number instanceof Double) {
            if(!(a.number instanceof Double)) {
                throw new IllegalArgumentException();
            }
            return ((Double)number < (Double)a.number);
        } else {
            if(!(a.number instanceof Long)) {
                throw new IllegalArgumentException();
            }
            return ((Long) number < (Long) a.number);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
