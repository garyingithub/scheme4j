package cn.galium.scheme4j;

import cn.galium.scheme4j.Tokenizer;

import static org.junit.Assert.*;

public class TokenizerTest {
    @org.junit.Test
    public void tokenize() throws Exception {
        assertTrue(new Tokenizer() {}.tokenize("").length == 0);
        assertTrue(new Tokenizer() {}.tokenize("(").length == 1);
        assertTrue(new Tokenizer() {}.tokenize("(key(hello)one )").length == 7);
    }

}