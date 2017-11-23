package cn.galium.scheme4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Tokenizer {

    @Autowired
    private Inport inport;

    public String[] tokenize(String expression) {
        Inport inport = new Inport(new ByteArrayInputStream(expression.getBytes()));
        List<String> list = new ArrayList<>();
        boolean finish = false;
        while (!finish) {
            String token = nextToken("\\s*(,@|[('`,)]|\"(?:[\\\\].|[^\\\\\"])*\"|;.*|[^\\s('\"`,;)]*)(.*)");
            if (StringUtils.isNoneBlank(token)) {
                list.add(token);
                finish = true;
            }
        }
        String[] result = new String[list.size()];
        return list.toArray(result);
    }

    public String nextToken(String regex) {

        Pattern tokenizer = Pattern.compile(regex);
        while (true) {
            if (StringUtils.isBlank(inport.line)) {
                inport.line = inport.scanner.nextLine();
            }
            if (StringUtils.isBlank(inport.line)) {
                return null;
            }
            Matcher m = tokenizer.matcher(inport.line);
            if (m.find() && !m.group(1).startsWith(";") && !inport.line.equals(m.group(2))) {

                inport.line = m.group(2);
                return m.group(1);

            } else {
                inport.line = "";
            }
        }
    }

    public String nextToken() {
        return nextToken("\\s*(,@|[('`,)]|\"(?:[\\\\].|[^\\\\\"])*\"|;.*|[^\\s('\"`,;)]*)(.*)");
    }

}

