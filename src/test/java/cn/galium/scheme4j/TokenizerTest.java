package cn.galium.scheme4j;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class TokenizerTest {
    @org.junit.Test
    public void tokenize() throws Exception {
        assertTrue(new Tokenizer() {
        }.tokenize("").length == 0);
        assertTrue(new Tokenizer() {
        }.tokenize("(").length == 1);
        assertTrue(new Tokenizer() {
        }.tokenize("(key(hello)one )").length == 7);
    }

    @Test
    public void testRegex() {
        String pattern = "\\s*(,@|[('`,)]|\"(?:[\\\\].|[^\\\\\"])*\"|;.*|[^\\s('\"`,;)]*)(.*)";

        String content = "(+ 5 6)";

        Pattern r = Pattern.compile(pattern);

        while (StringUtils.isNoneBlank(content)) {
            Matcher m = r.matcher(content);


            if (m.find()) {
                System.out.println(m.group(0));

                System.out.println(m.group(1));
                System.out.println(m.group(2));
                if (!content.equals(m.group(2))) {
                    content = m.group(2);
                } else {
                    content = "";
                }
            }
        }

    }
}