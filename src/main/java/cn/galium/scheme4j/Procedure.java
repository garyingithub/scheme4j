package cn.galium.scheme4j;

public class Procedure {

    String[] params;

    Environment environment;

    Func body;

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
    interface Func {
        Object call(Object[] params);
    }
}
