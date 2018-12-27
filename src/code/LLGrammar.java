package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


public class LLGrammar extends Grammar {
    /***
     * 架构 全局变量 首先每个非终结符对应的First集Follow集必须初始化和分配空间 初始的set的size大小为0 终结符的first集为本身
     * lookedhead表示当前正在匹配的字母 first函数用来求first集 follow函数。。。。 select函数求。。。
     * is_left_left判断是否为LL(1)型文法 match函数用来判断lookedhead和传入参数是否匹配 返回true/false
     * Parse_S函数是开始符S对应的分析程序
     */
//    String S; // 开始符
//    final String Null = null;
//    HashSet<String> VT; // 终结符集
//    HashSet<String> VN; // 非终结符集
//    HashMap<String, HashSet<String>> First; // first集合
//    HashMap<String, HashSet<String>> Follow; // follow集合
//    HashMap<String, Boolean> Nullable; // 记录产生式是否可以推出ε
//    HashMap<String, HashSet<String>> expSet; // 产生式集合
    public LLGrammar(String S, HashSet<String> VT, HashSet<String> VN, HashMap<String,
            HashSet<String>> expSet) {
        this.S = S;
        this.VT = VT;
        this.VN = VN;
        this.expSet = expSet;
    }

    public void Init() {
        /**
         * 给每个非终结符对应的First集Follow集必须初始化和分配空间 初始的set的size大小为0
         * 初始化所有字母表的nullable为false
         * 置所有终结符的first集为本身
         */
        First = new HashMap<String, HashSet<String>>();
        Follow = new HashMap<String, HashSet<String>>();
        Nullable = new HashMap<String, Boolean>();
        // 给非终结符的first集合分配空间
        for (String str : VN) {
            HashSet<String> temp = new HashSet<String>();
//            assertNotNull(First);
//            System.out.println(temp==null);
//            System.out.println(str);
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
        // 初始ε的first集为本身
        First.put(null, null);

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

    /**
     * 描述: 判断参数中的str是否在终结符集合中.
     *
     * @param str 要判断的文法字符
     * @return boolean
     * @throws
     * @author DingKe
     * @since 2018/12/27 0027 11:50
     */
    public boolean isTerminal(String str) {
        return true ? VT.contains(str) : false;
    }

    public HashSet<String> getFirst(String left) {
        /**
         * 用递归的方式来求参数中left的first集合
         */
        if (!First.get(left).isEmpty()) { // 如果已经求出了first集 返回即可
            return First.get(left);
        } else {
            HashSet<String> rightSet = expSet.get(left);
            for (String right : rightSet) {
                HashSet<String> set = First.get(left);// 先取出first集合
                set.addAll(getFirst(String.valueOf(right.charAt(0))));// 添加元素
                First.put(left, set); // 重新加入到first集
            }
        }

        return First.get(left);
    }

    public void my_first(LLExp exp) {
        /**
         * 求参数中exp左部的first集合
         */
        ArrayList<String> rights = exp.getRightList(); // 取出exp产生式右部分的集合
        for (String right : rights) {
            HashSet<String> set = expSet.get(right); // 取出right的产生式集合
            for (String t : // 遍历右边表达式的首个字母
                    set) {
                boolean FLAG = false;
                while (FLAG) {
                    HashSet<String> t_set = expSet.get(t);
                    for (String tt : t_set) {
                        if (isTerminal(tt)) { // 找到终结符了 先不返回 置标记为true 继续遍历寻找终结符
                            FLAG = true;
                            HashSet<String> right_set = First.get(exp.left);// 取出first集合
                            right_set.add(tt); // 添加元素
                            First.put(exp.left, right_set); // 整体添加到对应first集合
                        }
                    }
                }
            }
        }
    }

    public boolean isNullable(char[] seq) {
        /**
         * 检查seq中的每一个符号是否都可以推空
         * 若是返回true 否则返回false
         */
        if (seq.length == 0) {
            return true;
        }
        for (int i = 0; i < seq.length; i++) {
            if (String.valueOf(seq[i]) == "Y") {
                System.out.println("^^^^^" + Nullable.get("Y"));
            }
            if (!Nullable.get(String.valueOf(seq[i]))) { // 一旦有一个不能推空 返回false
                return false;
            }
        }
        return true;
    }

    /**
     * 描述: TODO.
     *
     * @return void
     * @throws
     * @author DingKe
     * @since 2018/12/26 0026 22:32
     */
    public void nullable() {
        /**
         * 判断所有非终结符是否可以推空
         * 是 则置nullable为true 否 则置false
         */
        while (true) {
            boolean FLAG = false;
            for (String vn :
                    VN) {
                if (!expSet.containsKey(vn)) {
                    continue;
                }
                // 下面遍历来判断vn的所有产生式能否推空
                HashSet<String> strings = expSet.get(vn); // 取出左部为vn的表达式集合
                Iterator iterator = strings.iterator();
                while (iterator.hasNext()) {
                    String exp = (String) iterator.next();
                    if (exp == Null) { // 如果直接可以推空
                        Boolean bool = Nullable.get(vn);
                        Nullable.put(vn, true);
                        if (bool == false) {
                            FLAG = true;
                        }
                        continue;
                    }
                    boolean res = false;
                    res = isNullable(exp.toCharArray()); // 判断exp是否可以推空
                    if (res) { // 如果vn开始符的某一个产生式可以推空 就置Nullable集合为true
                        Boolean bool = Nullable.get(vn);
                        Nullable.put(vn, res);
                        if (bool == false) {
                            FLAG = true;
                        }
                    }
                }

            }
            if (!FLAG) { // 如果大小未变 则退出while循环
                break;
            }
        }
    }

    /**
     * 描述: TODO.
     *
     * @param expSet 产生式集合
     * @return java.util.HashMap<java.lang.String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               java.util.HashSet                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               <                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               java.lang.String>>
     * @throws
     * @author DingKe
     * @since 2018/12/26 0026 22:10
     */
    public void first(HashMap<String, HashSet<String>> expSet) {


    }

    /**
     * 描述: TODO.
     *
     * @param set 要进行移除ε处理的first集合
     * @return java.util.HashSet<java.lang.String>
     * @throws
     * @author DingKe
     * @since 2018/12/27 0027 18:55
     */
    public HashSet<String> removeNullCharFromFirstSet(HashSet<String> set) {
        HashSet<String> res = new HashSet<>();
        if (set.contains(null)) {
            res = (HashSet<String>) set.clone();
            System.out.println("------去除ε后的集合--");
            System.out.println(res);
            res.remove(null);
            return res;
        }
        return set;
    }

    public boolean AllCharNullBefore(String left, String right, int j) {
        // 处理右部字符可推空的情况
        boolean FLAG = false;
        HashSet<String> set = this.First.get(left);
        String value = String.valueOf(right.charAt(j)); // 取出j位置字符
        // 将j字符的first集加入到left的first集
        HashSet<String> newSet = First.get(value);
        // 说明left的First集合不含ε 而value的First集含ε 需要将去掉ε的first集合并入left的First集
        if (!First.get(left).contains(null) && newSet.contains(null)) {
            newSet = removeNullCharFromFirstSet(newSet); // 将要并入的集合移除ε
        }
        int size = set.size();
        set.addAll(newSet); // 合并集合
        if (set.size() > size) { // 有新元素加入
            FLAG = true;
        }
        this.First.put(left, set);
        return FLAG;
    }

    /**
     * 描述: 先求first集 再求follow集.
     *
     * @param expSet
     * @return java.util.HashMap<java.lang.String               ,               java.util.HashSet               <               java.lang.String>>
     * @throws
     * @author DingKe
     * @since 2018/12/27 0027 20:21
     */
    public HashMap<String, HashSet<String>> follow(HashMap<String, HashSet<String>> expSet) {
        int i, j, k;
        boolean FLAG;// 跳出while循环标记
        HashMap<String, HashSet<String>> res = new HashMap<>(); // 存放follow集的set集合
        while (true) {
            FLAG = false;
            for (String s : expSet.keySet()) { // 遍历所有产生式
                String left = s; // 当前产生式左部非终结符
                HashSet<String> rightSet = expSet.get(left); // 当前产生式右部set集合
                for (String right : rightSet) { // 遍历left为左部的所有产生式
                    if (right == null) { // right为null 即ε
                        continue;
                    }
                    k = right.length();
                    for (i = 0; i < k; i++) {
                        // 求first集
                        boolean SKIP = false; // 跳过标记
                        String vn = String.valueOf(right.charAt(i));
                        HashSet<String> set = this.First.get(left); // left的first集
                        String firstLetter = String.valueOf(right.charAt(0)); // 取出右部第一个字符
                        // 将firstLetter的first集加入
                        int size = set.size();
                        set.addAll(removeNullCharFromFirstSet(this.First.get(firstLetter)));
                        if (set.size() > size) { // 有新元素加入
                            FLAG = true;
                        }
                        this.First.put(left, set);
                        // 如果右部只有一个元素 跳过下面的循环求first的部分
                        if (right.length() == 1) {
                            if (right == null) { // 右部只有一个元素ε
                                set.add(null); // 将ε加入left的first集
                            }
                            SKIP = true;
                        }
                        for (j = i + 1; j < k; j++) {
                            // 求first集 处理右部字符可推空的情况
                            if (!SKIP && j < k - 1 && isNullable(right.substring(0, j).toCharArray())) { // j字符前面全可推空
                                FLAG = AllCharNullBefore(left, right, j);
                            }

//                      求vn的follow集
                            // 判断i到j之间的串是否可以全部为空
                            if (isNullable(right.substring(i, j).toCharArray())) {
                                if (j + 1 < right.length()) {
                                    String str = String.valueOf(right.charAt(j + 1));
                                    res.put(vn, First.get(str)); // 将j后一个符号的first集加入到所求vn的follow集中
                                    FLAG = true;

                                }
                            }
                            // 所求符号后面全部可推空的时候 将产生式左边的follow集加入到所求的follow集
                            if (isNullable(right.substring(i, k - 1).toCharArray())) {
                                res.put(vn, First.get(left)); // 将left的first集加入到所求vn的follow集中
                                FLAG = true;
                            }
                        }

                    }

                }
                // 若在此轮迭代计算中temp集合没有变化 说明计算完毕 退出循环
                if (!FLAG) {
                    break;
                }
            }
            return res;
        }
    }
}