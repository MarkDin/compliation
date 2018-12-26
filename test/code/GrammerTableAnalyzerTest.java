package code;

import java.util.HashMap;
import java.util.HashSet;

public class GrammerTableAnalyzerTest {

    LLGrammar Test;
    public void getTable() {
        HashSet<String> VT = new HashSet<String>(); // 终结符集
        HashSet<String> VN = new HashSet<String>(); // 非终结符集
        HashMap<String, HashSet<String>> expSet = new HashMap<String, HashSet<String>>(); // 产生式集合
        HashSet<String> temp = new HashSet<>();
        String endChar = "#";
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
        temp.add("c");
        expSet.put("E", temp);
        Test = new LLGrammar("S", VT, VN, expSet);
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
/*
//        // 测试输入是否正确
        for (String vn :
                VN) {
            System.out.println("vn" + vn + ":");
            HashSet<String> exps = expSet.get(vn);
            for (String exp :
                    exps) {
                System.out.println(exp);
            }

//            // First集
//            System.out.println(vn+"的first集为");
//            System.out.println("--------------------");
//            for (String f :
//                    Test.First.get(vn)) {
//                System.out.println(f);
//            }
//            System.out.println("--------------------");
//            // Follow集
//            System.out.println(vn+"的follow集为");
//            System.out.println("--------------------");
//            for (String f :
//                    Test.Follow.get(vn)) {
//                System.out.println(f);
//            }
//            System.out.println("--------------------");
//            System.out.println();
//        }
            HashMap<Pair<String, String>, Exp> table = new HashMap<Pair<String, String>, Exp>();
            GrammerTableAnalyzer.getTable(table, Test);
            for (Pair p :
                    table.keySet()) {
                System.out.println("key: " + p);
                System.out.println("value: " + table.get(p).left + "->" + table.get(p).rightList);
            }
        }
*/
    }

}