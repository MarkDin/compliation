package code;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LLGrammarTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void follow() {
        GrammerTableAnalyzerTest test = new GrammerTableAnalyzerTest();
        test.getTable();
        LLGrammar Test = test.Test;
        Test.follow(Test.Follow);

        System.out.println(Test.Follow);
    }
}