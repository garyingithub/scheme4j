package cn.galium.scheme4j;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EvaluatorTest {

    Evaluator evaluator = new Evaluator() {};
    Parser parser = new Parser() {};
    Tokenizer tokenizer = new Tokenizer() {};

    private Object evaluateExpression(String exp) {
        return evaluator.eval(parser.parse(tokenizer.tokenize(exp)), Environment.rootEnv);
    }

    @Test
    public void testEval() {
        assertTrue(evaluateExpression("10").toString().equals("10"));
        assertTrue(evaluateExpression("(quote 10)").toString().equals("10"));
        assertTrue(evaluateExpression("(if (< 3 5) 3 5)").toString().equals("3"));
        assertTrue(evaluateExpression("(if (< 3 5) 5 3)").toString().equals("5"));
    }
}