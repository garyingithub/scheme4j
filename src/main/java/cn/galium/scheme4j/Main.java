package cn.galium.scheme4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "cn.galium.scheme4j")
public class Main {
    public static void main(String[] args) {


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);

        Evaluator evaluator = applicationContext.getBean(Evaluator.class);

        Parser parser = applicationContext.getBean(Parser.class);
        while (true) {
            System.out.print("lisp>");

            Object x = parser.parse();

            System.out.println(evaluator.eval(x, Environment.rootEnv));
        }

    }
}
