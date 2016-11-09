package org.naddeo.elm.parser;

import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args)
    {
        ElmParser p = new ElmParser(new ElmLexer(new InputStreamReader(System.in)));
        try {
//            p.parse();
            System.out.println("> " + p.parse().value);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
