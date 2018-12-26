package code;
//import compliation_01;

import jdk.nashorn.internal.ir.VarNode;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 消除有害规则和消除左递归
 */

public class eraseBadRuleAndLeftRecursion {
    LLGrammar Test = (LLGrammar)new data_case().analyzeData();
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
        Exp newExp = new LLExp(left);
        newExp.addRight(Z);
        for (Object R: // 构造新的右部产生式集合
                exp.rightList) {
            if (!R.equals(right)) { // 如果不是产生递归的那个产生式
                newExp.rightList.add(R + Z); // 在产生式右部末尾加上Z
            }
        }

    }

    public boolean hasVN(ArrayList<String> list) {
        /**
         * 判断list中所有右部是否都是以终结符开头
         * 是 返回true
         * 否 返回false
         */
        for (String right :
                list) {
            String t = String.valueOf(right.charAt(0));
            if (Test.VN.contains(t)) { // 最左边还是非终结符
                return false;
            }
        }
        return true;
    }

    public void extractComonLeftFactor(Exp exp) {
        /**
         * 作用:提取给出的单个产生式exp的公共左因子
         * 1.先将隐式的公因子化为显示的
         */
        // 先将隐式的公因子化为显示的
        // 步骤为将产生式右部最左边的非终结符进行替换,直到最左边为终结符
        String left = exp.left;
        for (String right : // 遍历left为左部的所有产生式
                (String[]) exp.rightList.toArray()) {

            String t = String.valueOf(right.charAt(0)); // t为右部最左字符
            if (Test.VT.contains(t)) { // 如果t是终结符
                continue;
            }
            // 进行替换
            while (hasVN(exp.rightList)) { // 循环替换直到最左边不是非终结符
                String substring = right.substring(1, right.length()); // 去掉第一个字符后的String
                HashSet<String> sets = Test.expSet.get(t); // 取出待替换的全部产生式set集合
                for (String r :
                        sets) {
                    exp.rightList.add(r + substring); // 进行替换拼接 并加入到rightlist
                }
                exp.rightList.remove(right); // 删除当前左边为非终结符的右部
            }
            //
        }
    }


}
