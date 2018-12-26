package code;

import java.util.HashMap;
import java.util.HashSet;

public class data_case {
    public LLGrammar nullable() {
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
        temp = new HashSet<>();
        // S
        temp.add("Y");
        expSet.put("S", temp);
        LLGrammar Test = new LLGrammar("S", VT, VN, expSet);
        return Test;
    }
}
