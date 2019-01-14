package code;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.HashSet;

import  org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LLGrammarTest {
    GrammerTableAnalyzerTest test = new GrammerTableAnalyzerTest();
    LLGrammar Test;
    @Before
    public void setUp() throws Exception {
        test.getTable(); // 调用getTable函数初始化数据
        // 将成员变量传给Test
        Test = new LLGrammar(test.Test.S, test.Test.VT, test.Test.VN, test.Test.expSet);
    }

    @Test
    public void follow() {
        /*
        HashMap<String, HashSet<String>> expectedFirst = test.Test.First;
        HashMap<String, HashSet<String>> expectedFollow = test.Test.Follow;
        Test.Init();
        System.out.println("预期的first集为:");
        System.out.println(expectedFirst);
        Test.nullable();
        HashMap<String, HashSet<String>> expected = Test.follow(Test.expSet);
        System.out.println("所求的first集为:");
        System.out.println(Test.First);
        System.out.println("预期的follow集为:");
        System.out.println(expectedFollow);
        System.out.println("所求的follow集为:");
        System.out.println(expected);
        //assertEquals(expectedFirst, Test.First);
        // System.out.println(Test.expSet.get("D"));
        assertEquals(expectedFirst, Test.First);
        */
        Test.Init();
        Test.nullable();
        System.out.println(Test.follow(Test.expSet));


    }
}