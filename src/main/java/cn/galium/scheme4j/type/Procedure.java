package cn.galium.scheme4j.type;

import cn.galium.scheme4j.Environment;
import cn.galium.scheme4j.Evaluator;

import java.util.Arrays;

/**
 * @author mac
 */
public class Procedure {

    /**
     * parameters' name
     */
    public Symbol[] params;

    public Environment environment;

    public Object body;

    Object call(Evaluator evaluator, Object[] args) {
        return evaluator.eval(body, new Environment(params, args, environment));
    }

    public Procedure(Symbol[] params, Object body, Environment environment) {
        this.params = params;
        this.environment = environment;
        this.body = body;
    }

    public Procedure(String[] params, Object body, Environment environment) {
        this.params = Arrays.stream(params).map(Symbol::new).toArray(Symbol[]::new);
        this.environment = environment;
        this.body = body;
    }

    public Procedure(String[] params, Func body, Environment environment) {
        this.params = Arrays.stream(params).map(Symbol::new).toArray(Symbol[]::new);
        this.environment = environment;
        this.body = body;
    }

    @FunctionalInterface
    public interface Func {
        Object call(Object[] params);
    }
}
