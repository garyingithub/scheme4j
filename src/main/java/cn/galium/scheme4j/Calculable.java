package cn.galium.scheme4j;

public interface Calculable<T> {

    T add(T other);

    T minus(T other);

    T multiply(T other);

    T divide(T other);

    T mod(T other);
}
