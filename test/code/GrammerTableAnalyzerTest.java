package code;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

public class GrammerTableAnalyzerTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        HashSet<String> VT = new HashSet<String>(); // 终结符集
        HashSet<String> VN = new HashSet<String>(); // 非终结符集
        HashMap<String, HashSet<String>> expSet = new HashMap<String, HashSet<String>>(); // 产生式集合
        HashSet<String> temp = new HashSet<>();
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

        // A A→BCc | gDB
        temp.add("BCc");
        temp.add("gDB");
        expSet.put("A", temp);
        temp = new HashSet<>();
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
        // E E→gAf | c
        temp.add("gAf");
        temp.add("c");expSet.put("D", temp);
        expSet.put("E", temp);
        compliation_02 Test = new compliation_02("S", VT, VN, expSet);

        // 测试输入是否正确
        for (String vn :
                VN) {
            System.out.println("vn"+vn+":");
            HashSet<String> exps = expSet.get(vn);
            for (String exp :
                    exps) {
                System.out.println(exp);
            }
            System.out.println();
        }
    }

    @Test
    public void getTable() {

    }
}