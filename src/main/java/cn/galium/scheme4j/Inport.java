package cn.galium.scheme4j;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Scanner;

@Component
public class Inport {

    InputStream inputStream = System.in;
    public String line = "";

    public Scanner scanner = new Scanner(inputStream);

    public Inport(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Inport() {

    }


}
