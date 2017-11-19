package cn.galium.scheme4j;

import org.omg.CORBA.OBJ_ADAPTER;

import java.util.*;

public interface Parser {

    default Object parse(String[] tokens) {

        List<Object> root = new ArrayList<>();
        Stack<Object> stack = new Stack<>();
        stack.push(root);

        for(String token : tokens) {
            switch (token) {
                case "(" :
                    List<Object> node = new ArrayList<>();
                    stack.push(node);
                    break;
                case ")" :
                    List<Object> top = (List<Object>) stack.pop();
                    ((List<Object>)stack.peek()).add(top);
                    break;
                default:
                    try {
                        ((List<Object>)stack.peek()).add(new Number(token));
                    } catch (NumberFormatException e) {
                        ((List<Object>)stack.peek()).add(token);
                    }
            }
        }

        return root.get(0);

    }

}
