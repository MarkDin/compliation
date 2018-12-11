package code;
//import compliation_01;

import java.util.HashSet;

/**
 * 消除有害规则和消除左递归
 */

public class eraseBadRuleAndLeftRecursion {
    compliation_02 Test = new data_case().analyzeData();
    public void eliminateLeftRecursion(String left, String right, Exp exp){
        /**
         *
         */
        String X = left;
        String Y = right.substring(1, right.length() - 1); // 去掉第一个位置的产生递归的非终结符
        String Z = Y + 'Z';
        Test.VN.add("Z"); // 将新的非终结符加入VN集合
        HashSet<String> set = new HashSet<>();
        set.add(null);
        set.add(Z);
        Test.expSet.put("Z",set); // 将新的产生式加入expSet
        Exp newExp = new Exp(left);
        newExp.addRight(Z);
        for (String R: // 构造新的右部产生式集合
             exp.rightList) {
            if (!R.equals(right)) { // 如果不是产生递归的那个产生式
                newExp.rightList.add(R + Z); // 在产生式右部末尾加上Z
            }
        }

    }

}
