package cn.galium.scheme4j;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class Environment extends HashMap<String, Object>{

    private Environment outer;

    static Environment rootEnv;

    static {
        rootEnv = new Environment();
        rootEnv.put("+", new Procedure(new String[] {"a", "b"}, objects -> ((Number)objects[0]).add((Number) objects[1]), rootEnv));
        rootEnv.put("-", new Procedure(new String[] {"a", "b"}, objects -> ((Number)objects[0]).minus((Number) objects[1]), rootEnv));
        rootEnv.put("*", new Procedure(new String[] {"a", "b"}, objects -> ((Number)objects[0]).multiply((Number) objects[1]), rootEnv));
        rootEnv.put("/", new Procedure(new String[] {"a", "b"}, objects -> ((Number)objects[0]).divide((Number) objects[1]), rootEnv));
        rootEnv.put("<", new Procedure(new String[] {"a", "b"}, objects -> ((Number)objects[0]).lt((Number) objects[1]), rootEnv));
        rootEnv.put("begin", new Procedure(new String[] {"x"}, objects -> objects[objects.length - 1], rootEnv));
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

