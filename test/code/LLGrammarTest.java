package code;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import  org.junit.jupiter.api.Assertions;

public class LLGrammarTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void follow() {
        GrammerTableAnalyzerTest test = new GrammerTableAnalyzerTest();
        test.getTable();
        LLGrammar Test = test.Test;
        HashMap<String, HashSet<String>> expected = Test.follow(Test.expSet);
        System.out.println(expected.isEmpty());
        System.out.println(Test.Follow);
        for (String vn : expected.keySet()) {
            HashSet<String> follow = expected.get(vn);
            System.out.println(vn+"的follow集为:");
            for (String s : follow) {
                System.out.println(s);
            }
            System.out.println("---------------------------------");
        }
    }
}