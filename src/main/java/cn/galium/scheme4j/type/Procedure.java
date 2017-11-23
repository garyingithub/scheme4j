package cn.galium.scheme4j.type;

import cn.galium.scheme4j.Environment;
import cn.galium.scheme4j.Evaluator;

public class Procedure {

    public String[] params;

    public Environment environment;

    public Func body;

    Evaluator evaluator = new Evaluator() {};

    Object call(Object[] args) {
        return evaluator.eval(body, new Environment(params, args, environment));
    }

    public Procedure(String[] params, Func body, Environment environment) {
        this.params = params;
        this.environment = environment;
        this.body = body;
    }

    @FunctionalInterface
    public interface Func {
        Object call(Object[] params);
    }
}
