package cn.galium.scheme4j;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Tokenizer tokenizer = new Tokenizer() {};
        Parser parser = new Parser() {};
        Evaluator evaluator = new Evaluator() {};

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("lisp>");
            Object val = evaluator.eval(parser.parse(tokenizer.tokenize(scanner.nextLine())), Environment.rootEnv);
            if(val != null) {
                System.out.println(String.valueOf(val));
            }
        }

    }
}
