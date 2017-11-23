package cn.galium.scheme4j.type;

import java.util.HashMap;
import java.util.Map;

public class Symbol {

    String content;

    public Symbol(String content) {
        this.content = content;
    }

    static Map<String, Symbol> symbolTable = new HashMap<>();

    static Symbol sym(String s) {
        if (!symbolTable.containsKey(s)) {
            symbolTable.put(s, new Symbol(s));
        }
        return symbolTable.get(s);
    }

    public static final Symbol QUOTE = sym("quote");
    public static final Symbol IF = sym("if");
    public static final Symbol SET = sym("set!");
    public static final Symbol DEFINE = sym("define");
    public static final Symbol LAMBDA = sym("lambda");
    public static final Symbol BEGIN = sym("begin");
    public static final Symbol DEFINEMACRO = sym("define-macro");
    public static final Symbol QUASIQUOTE = sym("quosiquote");
    public static final Symbol UNQUOTE = sym("unquote");
    public static final Symbol UNQUOTESPLICING = sym("unquote-splicing");

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Symbol symbol = (Symbol) o;

        return content.equals(symbol.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return content;
    }
}
