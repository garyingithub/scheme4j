package cn.galium.scheme4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "cn.galium.scheme4j")
public class Main {
    public static void main(String[] args) {


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);

//        Tokenizer tokenizer = new Tokenizer() {};
//        Parser parser = new Parser() {};
//        Evaluator evaluator = new Evaluator() {};

        Evaluator evaluator = applicationContext.getBean(Evaluator.class);

        Parser parser = applicationContext.getBean(Parser.class);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("lisp>");

//            Object val = evaluator.eval(parser.parse(tokenizer.tokenize(scanner.nextLine())), Environment.rootEnv);
//            if(val != null) {
//                System.out.println(String.valueOf(val));
//            }
            Object x = parser.parse();

            System.out.println(evaluator.eval(x, Environment.rootEnv));
        }

    }
}
