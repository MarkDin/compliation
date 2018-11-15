package code;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

public class compliation_02Test {

    @Before
    public void setUp() throws Exception {

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
        // VT
        VT.add("d");
        VT.add("a");
        VT.add("c");
        // expSet
        // Z
        temp.add("d");
        temp.add("XYZ");
        expSet.put("Z", temp);
        temp.clear();
        // Y
        temp.add(null);
        temp.add("c");
        expSet.put("Y", temp);
        temp.clear();
        // X
        temp.add("Y");
        temp.add("a");
        expSet.put("X", temp);
        compliation_02 test = new compliation_02("S", VT, VN, expSet);
        test.Init();
        ArrayList res = new ArrayList();
        for (String t :
                VT) {
            res.add(test.First.get(t));
            System.out.println(test.First.get(t));
        }
        ArrayList list = new ArrayList();
        list.add("a");
        list.add("d");
        list.add("c");
//        assertArrayEquals(list, res);
        assertEquals(list, res);
    }
}