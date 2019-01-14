package code;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

public class GrammerTableAnalyzerTest {

    LLGrammar Test;
    @Before
    public void getTable() {

        HashSet<String> VT = new HashSet<String>(); // 终结符集
        HashSet<String> VN = new HashSet<String>(); // 非终结符集
        HashMap<String, HashSet<String>> expSet = new HashMap<String, HashSet<String>>(); // 产生式集合
        HashSet<String> temp = new HashSet<>();
        String endChar = "#";

        VN.add("A");
        VN.add("B");
        VN.add("S");
        VN.add("C");
        VT.add("a");
        VT.add("b");
        VT.add("c");
        temp.add("AB");
        expSet.put("S", temp);
        temp = new HashSet<>();
        temp.add(null);
        temp.add("a");
        expSet.put("A", temp);
        temp = new HashSet<>();
        temp.add("b");
        temp.add(null);
        temp.add("Cc");
        expSet.put("B", temp);
        temp = new HashSet<>();
        temp.add("aAB");
        expSet.put("C", temp);
        Test = new LLGrammar("S", VT, VN, expSet);
        Test.Init();

        /*
        // VN
        VN.add("A");
        VN.add("B");
        VN.add("C");
        VN.add("D");
        VN.add("E");
        // VT
        VT.add("a");
        VT.add("b");
        VT.add("c");
        VT.add("d");
        VT.add("f");
        VT.add("g");
        // expSet


        // B B→bCDE | ε
        temp.add(null);
        temp.add("bCDE");
        expSet.put("B", temp);
        temp = new HashSet<>();

        //C C→DaB | ca
        temp.add("DaB");
        temp.add("ca");
        expSet.put("C", temp);
        temp = new HashSet<>();
        // D D→dD | ε
        temp.add("dD");
        temp.add(null);
        expSet.put("D", temp);
        temp = new HashSet<>();
        // A A→BCc | gDB
        temp.add("BCc");
        temp.add("gDB");
        expSet.put("A", temp);
        temp = new HashSet<>();
        // E E→gAf | c
        temp.add("gAf");
        temp.add("c");
        expSet.put("E", temp);
        Test = new LLGrammar("A", VT, VN, expSet);
        Test.Init();
        // 手动构造first集
        HashSet<String> set = new HashSet<>();
        // A
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("g");
        Test.First.put("A", set);
        // B
        set = new HashSet<>();
        set.add("b");
        set.add(null);
        Test.First.put("B", set);
        // C
        set = new HashSet<>();
        set.add("a");
        set.add("c");
        set.add("d");
        Test.First.put("C", set);
        // D
        set = new HashSet<>();
        set.add("d");
        set.add(null);
        Test.First.put("D", set);
        // E
        set = new HashSet<>();
        set.add("c");
        set.add("g");
        Test.First.put("E", set);

        // 手动构造follow集
        set = new HashSet<>();
        // A
        set.add("f");
        set.add(endChar);
        Test.Follow.put("A", set);
        // B
        set = new HashSet<>();
        set.add("a");
        set.add("c");
        set.add("d");
        set.add("g");
        set.add("f");
        set.add(endChar);
        Test.Follow.put("B", set);
        // C
        set = new HashSet<>();
        set.add("g");
        set.add("c");
        set.add("d");
        Test.Follow.put("C", set);
        // D
        set = new HashSet<>();
        set.add("a");
        set.add("c");
        set.add("b");
        set.add("g");
        set.add("f");
        set.add(endChar);
        Test.Follow.put("D", set);
        // E
        set = new HashSet<>();
        set.add("a");
        set.add("c");
        set.add("d");
        set.add("g");
        set.add("f");
        set.add(endChar);
        Test.Follow.put("E", set);
        */
    }
    @Test
    public void test() {
        //LLGrammar grammar = new LLGrammar(Test.S, Test.VT, Test.VN, Test.expSet);
        GrammerTableAnalyzer analyzer = new GrammerTableAnalyzer("ab");
        analyzer.getTable(analyzer.table, Test);
        analyzer.analyze("S", Test);
    }

}