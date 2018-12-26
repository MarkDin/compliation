package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public abstract class Grammar {

    public String S; // 开始符
    public final String Null = null;
    public HashSet<String> VT; // 终结符集
    public HashSet<String> VN; // 非终结符集
    public HashMap<String, HashSet<String>> First; // first集合
    public HashMap<String, HashSet<String>> Follow; // follow集合
    public HashMap<String, Boolean> Nullable; // 记录产生式是否可以推出ε
    public HashMap<String, HashSet<String>> expSet; // 产生式集合

//    public Grammar(String S, HashSet<String> VT, HashSet<String> VN, HashMap<String,
//            HashSet<String>> expSet) {
//        this.S = S;
//        this.VT = VT;
//        this.VN = VN;
//    }

    public  void Init(){
        /**
         * 给每个非终结符对应的First集Follow集必须初始化和分配空间 初始的set的size大小为0
         * 初始化所有字母表的nullable为false
         * 置所有终结符的first集为本身
         */
        HashSet<String> VT = new HashSet<String>(); // 终结符集
        HashSet<String> VN = new HashSet<String>(); // 非终结符集
        First = new HashMap<String, HashSet<String>>();
        Follow = new HashMap<String, HashSet<String>>();
        Nullable = new HashMap<String, Boolean>();
        // 给非终结符的first集合分配空间
        for (String str : VN) {
            HashSet<String> temp = new HashSet<String>();

            First.put(str, temp);
        }
        // 给非终结符的follow集合分配空间
        for (String str : VN) {
            HashSet<String> temp = new HashSet<String>();
            Follow.put(str, temp);
        }
        // 初始化终结符的first集为本身
        for (String t : VT) {
            HashSet<String> temp = new HashSet<String>();
            temp.add(t);
            First.put(t, temp);
        }
        // 初始化终结符的follow集为本身
//        for (String t : VT) {
//			HashSet<String> temp = new HashSet<>();
//			temp.add(t);
//			Follow.put(t, temp);
//        }

        // 初始化所有字母表的nullable为false
        for (String t :
                VN) {
            Nullable.put(t, false);
        }
        for (String t :
                VT) {
            Nullable.put(t, false);
        }
    }

    public boolean isTerminal(String str) {
        /**
         * 判断参数中的str是否在终结符集合中
         */
        return true ? VT.contains(str) : false;
    }

    public HashSet<String> getFirst(String left, boolean flag) {
        /**
         * 用递归的方式来求参数中left的first集合
         */
        return null;
    }

    public HashSet<String> getFirst(String left) {
        /**
         * 用递归的方式来求参数中left的first集合
         */
        return null;
    }

    public void first(Exp exp) {
        /**
         * 求参数中exp左部的first集合
         */

    }

    public boolean isNullable(char[] seq) {
        /**
         * 检查seq中的每一个符号是否都可以推空
         * 若是返回true 否则返回false
         */
        return Boolean.parseBoolean(null);
    }

    public void nullable() {
        /**
         * 判断所有非终结符是否可以推空
         * 是 则置nullable为true 否 则置false
         */

    }

    public void follow(String left, String right) {
        /**
         * 求参数中aim的follow集合 1.取出expSet右边的set集合 并遍历其右部 找到与包含aim的 命名为str str对应的左边为A
         * 2.若在str中aim后面没有字符 即aim在str中最右边 那么把follow(A)加入其follow集 3.若在str后面有字符* 如果*不能推出ε
         * 那么把first(*)中非空元素加入follow(aim) 如果*能推出ε 那么把
         */
        int i, j, k, size;
        //String right = exp.getRightList(); // 取出exp的右边的产生式
        HashSet<String> temp = new HashSet<>(); // 临时存放follow集的set集合
        k = right.length();
        size = 0; // 初始化follow集合大小为0
        for (i = 0; i < k; i++) {
            for (j = i + 1; j < k; j++) {
                char[] t = right.substring(i, j).toCharArray();
                System.out.println("测试1----str=");
                for (char a :
                        t) {
                    System.out.print(a);
                }
                if (isNullable(right.substring(i, j).toCharArray())) { // 判断i+1到j之间的串是否可以全部为空
                    if (j + 1 < right.length()) {
                        String str = String.valueOf(right.charAt(j + 1));
                        temp.addAll(First.get(str));// 将j后一个符号的first集加入到所求的follow集中
                    }
                }
                if (isNullable(right.substring(i, k - 1).toCharArray())) {
                    temp.addAll(Follow.get(left)); // 所求符号后面全部可推空的时候 将产生式左边的follow集加入到所求的follow集
                }
                if (temp.size() == size) { // 若在此轮迭代计算中temp集合没有变化 说明计算完毕 退出循环
                    break;
                }
                size = temp.size();
            }

        }

    }
}
