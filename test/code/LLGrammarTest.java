package code;

import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;

import  org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import javax.sound.midi.Soundbank;

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
//        System.out.println(Test.Follow);
        HashMap<String, HashSet<String>> expectedFirst = test.Test.First;
        HashMap<String, HashSet<String>> expectedFollow = test.Test.Follow;
        Test.Init();
        Test.nullable();
        HashMap<String, HashSet<String>> expected = Test.follow(Test.expSet);
        System.out.println(expected.isEmpty());
        System.out.println(Test.Nullable);
        System.out.println(Test.First);
        System.out.println(Test.Follow);
    }
}