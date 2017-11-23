package cn.galium.scheme4j;

import cn.galium.scheme4j.type.Number;
import cn.galium.scheme4j.type.Symbol;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Component
public class Parser {

    @Autowired
    private Tokenizer tokenizer;

    Object parse(String[] tokens) {

        List<Object> root = new ArrayList<>();
        Stack<Object> stack = new Stack<>();
        stack.push(root);

        for (String token : tokens) {
            switch (token) {
                case "(":
                    List<Object> node = new ArrayList<>();
                    stack.push(node);
                    break;
                case ")":
                    List<Object> top = (List<Object>) stack.pop();
                    ((List<Object>) stack.peek()).add(top);
                    break;
                default:
                    try {
                        ((List<Object>) stack.peek()).add(new Number(token));
                    } catch (NumberFormatException e) {
                        ((List<Object>) stack.peek()).add(token);
                    }
            }
        }
        return root.get(0);
    }

    Object parse() {

        String token = tokenizer.nextToken();
        switch (token) {
            case "(":
                List<Object> node = new ArrayList<>();
                while (true) {
                    Object element = parse();
                    if (element instanceof String && ")".equals(element)) {
                        return node;
                    } else {
                        node.add(element);
                    }
                }
            case ")":
                return ")";

            default:
                return atom(token);
        }
    }

    Object atom(String token) {
        if ("#t".equals(token)) {
            return Boolean.TRUE;
        }
        if ("#f".equals(token)) {
            return Boolean.FALSE;
        }
        if (StringUtils.isNoneBlank(token) && token.charAt(0) == '"') {
            return token.substring(1);
        }

        try {
            return new Number(token);
        } catch (NumberFormatException e) {
            return new Symbol(token);
        }
    }

}
