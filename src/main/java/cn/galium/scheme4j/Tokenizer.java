package cn.galium.scheme4j;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public interface Tokenizer {

    default String[] tokenize(String expression) {
        expression = expression.replace("(", " ( ");
        expression = expression.replace(")", " ) ");

        return Arrays.stream(expression.split(" ")).filter(StringUtils::isNoneBlank).toArray(String[]::new);
    }


}

