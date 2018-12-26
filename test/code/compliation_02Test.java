package code;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class compliation_02Test {

    @Before
    public void setUp() throws Exception {
//        HashMap<String, HashSet<String>> map = new HashMap<>();
//        ArrayList a = new ArrayList();
//        a.add("a");
//        a.add("b");
//        //a.clear();
//        HashSet set = new HashSet();
//        set.addAll(a);
//        map.put("set", set);
//        a.add("c");
//        a.add("d");
//        System.out.println(set);
//        System.out.println(map);
//        set.addAll(a);
//        System.out.println(a);
//        //map.put("a", a);
    }

    @After
    public void tearDown() throws Exception {
        }

    @Test
    public void init() {
        HashSet<String> VT = new HashSet<String>(); // 终结符集
        HashSet<String> VN = new HashSet<String>(); // 非终结符集
        HashMap<String, HashSet<String>> expSet = new HashMap<String, HashSet<String>>(); // 产生式集合
        HashSet<String> temp = new HashSet<>();
        // VN
        VN.add("X");
        VN.add("Y");
        VN.add("Z");
        VN.add("S");
        // VT
        VT.add("d");
        VT.add("a");
        VT.add("c");
        // expSet

        // Z
        temp.add("d");
        temp.add("XYZ");
        expSet.put("Z", temp);
        temp = new HashSet<>();
        // Y
        temp.add(null);
        temp.add("c");
        expSet.put("Y", temp);
        temp = new HashSet<>();

        // X
        temp.add("Y");
        temp.add("a");
        expSet.put("X", temp);
        LLGrammar test = new LLGrammar("S", VT, VN, expSet);
        test.Init();
        ArrayList res = new ArrayList();
        for (String t :
                VT) {
            res.add(test.First.get(t));
            System.out.println(t+"的first集合为"+test.First.get(t));
        }
//        for (String vn :
//                VN) {
//            System.out.println("vn:"+vn+" "+expSet.get(vn));
//        }
        test.follow("X", "a");
        test.follow("Y", "a");
        test.follow("Z", "XYZ");
        System.out.println(test.Follow);
    }
}