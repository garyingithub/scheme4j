package cn.galium.scheme4j;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class TokenizerTest {
    @org.junit.Test
    public void tokenize() throws Exception {
        assertTrue(new Tokenizer() {
        }.tokenize("").length == 0);
        assertTrue(new Tokenizer().tokenize("(").length == 1);
        assertTrue(new Tokenizer() {
        }.tokenize("(key(hello)one )").length == 7);
    }

    @Test
    public void testRegex() {
        String pattern = "\\s*(,@|[('`,)]|\"(?:[\\\\].|[^\\\\\"])*\"|;.*|[^\\s('\"`,;)]*)";

        String content = "(+ 5 6)";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(content);


        while (m.find()) {
            System.out.println(m.group(1));
        }


    }
}