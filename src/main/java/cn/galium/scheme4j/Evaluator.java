package cn.galium.scheme4j;

import cn.galium.scheme4j.type.Procedure;
import cn.galium.scheme4j.type.Symbol;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Evaluator {

    public Object eval(Object x, Environment environment) {
        while (true) {
            if (!(x instanceof List)) {
                if (x instanceof Symbol) {
                    return environment.find((Symbol) x).get(x);
                } else {
                    return x;
                }
            } else {

                List<Object> exp = (List<Object>) x;

                if (exp.get(0) instanceof Symbol) {
                    Symbol op = (Symbol) exp.get(0);
                    Object[] args = Arrays.copyOfRange(exp.toArray(), 1, exp.size());

                    if (Symbol.QUOTE.equals(op)) {
                        return args;
                    }

                    if (Symbol.IF.equals(op)) {
                        Object test = args[0];
                        Object conseq = args[1];
                        Object alt = args[2];

                        if ((Boolean) (eval(test, environment))) {
                            x = conseq;
                        } else {
                            x = alt;
                        }
                        continue;
                    }

                    if (Symbol.DEFINE.equals(op)) {
                        if (args[0] instanceof Symbol) {
                            Symbol symbol = (Symbol) args[0];
                            environment.put(symbol, eval(args[1], environment));
                            return null;
                        } else {
                            List<Object> list = (List<Object>) args[0];
                            Symbol symbol = (Symbol) list.get(0);

                            Symbol[] params = new Symbol[list.size()];
                            params = list.toArray(params);
                            params = Arrays.copyOfRange(params, 1, params.length);
                            environment.put(symbol, new Procedure(params, args[1], environment));
                            return null;
                        }
                    }

                    if (Symbol.SET.equals(op)) {
                        Symbol symbol = (Symbol) args[1];
                        environment.find(symbol).put(symbol, args[1]);
                        return null;
                    }

                    if (Symbol.LAMBDA.equals(op)) {
                        Symbol[] params = new Symbol[((List<Object>) args[0]).size()];
                        params = ((List<Object>) args[0]).toArray(params);
                        return new Procedure(params, args[1], environment);
                    }

                    if (Symbol.BEGIN.equals(op)) {
                        List<Object> list = (List<Object>) args[0];

                        for (int i = 0; i < list.size(); i++) {
                            eval(list.get(i), environment);
                        }
                        x = eval(list.get(list.size() - 1), environment);
                        continue;
                    }

                    Procedure procedure = (Procedure) eval(op, environment);
                    Object[] values = new Object[args.length];
                    for (int i = 0; i < values.length; i++) {
                        values[i] = eval(args[i], environment);
                    }

                    Environment inner = new Environment(procedure.params, values, environment);

                    if (procedure.body instanceof Procedure.Func) {
                        return ((Procedure.Func) (procedure.body)).call(values);
                    } else {
                        x = procedure.body;
                        environment = inner;
                    }
                }


            }
        }
    }
}
