package cn.galium.scheme4j;


import java.util.Arrays;
import java.util.HashMap;

public class Environment extends HashMap<String, Object>{

    private Environment outer;

    static Environment rootEnv;

    static {
        rootEnv = new Environment();
        rootEnv.put("+", new Procedure(new String[]{"a", "b"}, objects -> ((Calculable) objects[0]).add(objects[1]), rootEnv));
        rootEnv.put("-", new Procedure(new String[]{"a", "b"}, objects -> ((Calculable) objects[0]).minus(objects[1]), rootEnv));
        rootEnv.put("*", new Procedure(new String[]{"a", "b"}, objects -> ((Calculable) objects[0]).multiply(objects[1]), rootEnv));
        rootEnv.put("/", new Procedure(new String[]{"a", "b"}, objects -> ((Calculable) objects[0]).divide(objects[1]), rootEnv));
        rootEnv.put("<", new Procedure(new String[]{"a", "b"}, objects -> ((Comparable) objects[0]).compareTo(objects[1]) < 0, rootEnv));
        rootEnv.put(">", new Procedure(new String[]{"a", "b"}, objects -> ((Comparable) objects[0]).compareTo(objects[1]) > 0, rootEnv));
        rootEnv.put("=", new Procedure(new String[]{"a", "b"}, objects -> ((Comparable) objects[0]).compareTo(objects[1]) == 0, rootEnv));
        rootEnv.put("<=", new Procedure(new String[]{"a", "b"}, objects -> ((Comparable) objects[0]).compareTo(objects[1]) <= 0, rootEnv));
        rootEnv.put(">=", new Procedure(new String[]{"a", "b"}, objects -> ((Comparable) objects[0]).compareTo(objects[1]) >= 0, rootEnv));
        rootEnv.put("begin", new Procedure(new String[] {"x"}, objects -> objects[objects.length - 1], rootEnv));
        rootEnv.put("car", new Procedure(new String[]{"x"}, objects -> {
            Object[] list = (Object[]) objects[0];
            return list[0];
        }, rootEnv));
        rootEnv.put("cdr", new Procedure(new String[]{"x"}, objects -> {
            Object[] list = (Object[]) objects[0];
            return Arrays.copyOfRange(list, 1, list.length);
        }, rootEnv));
        rootEnv.put("cons", new Procedure(new String[]{"x", "y"}, objects -> new Object[]{objects[0], objects[1]}, rootEnv));
        rootEnv.put("eq?", new Procedure(new String[]{"x"}, objects -> objects[0].equals(objects[1]), rootEnv));


    }

    public Environment find(String symbol) {
        if(this.containsKey(symbol)) {
            return this;
        }
        return outer.find(symbol);
    }



    Environment() {

    }

    Environment(String[] keys, Object[] values, Environment outer) {
        for(int i = 0; i < keys.length; i++) {
            this.put(keys[i], values[i]);
        }
        this.outer = outer;
    }
}

