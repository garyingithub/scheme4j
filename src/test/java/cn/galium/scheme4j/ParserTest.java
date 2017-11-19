package cn.galium.scheme4j;

import org.junit.Test;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void parse() throws Exception {
        Parser parser = new Parser() {};
        Tokenizer tokenizer = new Tokenizer() {};
        assertTrue(((List<Object>)parser.parse(tokenizer.tokenize("()"))).size() == 0);

        assertTrue(((List<Object>)parser.parse(tokenizer.tokenize("(a)"))).size() == 1);
//        assertTrue(parser.parse(tokenizer.tokenize("(a)")).get(0).size() == 1);

        assertTrue(((List)parser.parse(tokenizer.tokenize("((b) (b))"))).size() == 2);
//        assertTrue(parser.parse(tokenizer.tokenize("((b) (b))")).get(0).size() == 2);


        assertTrue(((List)parser.parse(tokenizer.tokenize("(b (b))"))).size() == 2);

//        assertTrue(parser.parse(tokenizer.tokenize("(b (b))")).get(0).size() == 2);
    }

}