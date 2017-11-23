package cn.galium.scheme4j;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Scanner;

@Component
public class Inport {

    InputStream inputStream = System.in;
    public String line = "";

    public Scanner scanner;

    public Inport(InputStream inputStream) {
        this.inputStream = inputStream;
        scanner = new Scanner(inputStream);
    }

    public Inport() {
        this(System.in);
    }


}
