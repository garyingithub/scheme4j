package cn.galium.scheme4j;

import java.util.Arrays;
import java.util.List;

public interface Evaluator {

    default Object eval(Object x, Environment environment) {
        if(! (x instanceof List)) {
            if(x instanceof String) {
                return environment.find((String) x).get(x);
            } else {
                return x;
            }
        } else {
            List<Object> exp = (List<Object>)x;

            String op = String.valueOf(exp.get(0));
            Object[] args = Arrays.copyOfRange(exp.toArray(), 1, exp.size());

            if("quote".equals(op)) {
                return args[0];
            }

            if("if".equals(op)) {
                Object test = args[0];
                Object conseq = args[1];
                Object alt = args[2];

                if((Boolean)(eval(test, environment))) {
                    return eval(conseq, environment);
                }

                return eval(alt, environment);
            }

            if("define".equals(op)) {
                String symbol = (String) args[0];
                environment.put(symbol, eval(args[1], environment));
                return null;
            }

            if("set!".equals(op)) {
                String symbol = (String) args[1];
                environment.find(symbol).put(symbol, args[1]);
                return null;
            }

            if("lambda".equals(op)) {
                String[] params = new String[((List<Object>)args[0]).size()];
                params = ((List<Object>)args[0]).toArray(params);

                Procedure.Func body = (Procedure.Func) args[1];


                return new Procedure(params, body, environment);
            }

            Procedure procedure = (Procedure) eval(op, environment);
            Object[] values = Arrays.stream(args).map(o -> eval(o, environment)).toArray();
            return procedure.body.call(values);
        }
    }
}
