/**
 * 自顶向下分析方法的表驱动分析程序(语法表)
 */
package code;

import javafx.util.Pair;
import java.util.*;

import code.compliation_02.*;
import code.data_case.*;
import jdk.nashorn.internal.ir.VarNode;
import org.junit.Test;

public class GrammerTableAnalyzer {
    Stack<String> s; //堆栈
    String input;// 输入串
    HashMap<Pair<String, String>, Exp> table;// 预测分析表 key是非终结符和终结符组成 value是对应的表达式
//    data_case Test = new data_case();//////////-----------------------------测试用----------------------------
    String endChar = "#";

    public GrammerTableAnalyzer(String input) {
        this.s = new Stack<String>();
        this.input = input;
        this.table = new HashMap<Pair<String, String>, Exp>();
    }

    public static Exp findFirst(String X, String ch, HashMap<String, HashSet<String>> First, HashMap<String, HashSet<String>> expSet) {
        /**
         * 用于getTable函数调用
         * 来查找非终结符X的First集中能推出ch的产生式
         * 并返回这个产生式
         */
        //HashMap<String, HashSet<String>> First = First;
        if (First.containsKey(X) && First.get(X).contains(ch)) { // 判断ch是否存在于X的First集中
            for (String right :
                    expSet.get(X)) { // 遍历X的全部产生式
                if (right == null) {
                    continue;
                }
//                System.out.println("测试一"+right.charAt(0));
//                System.out.println("测试二"+ch.toCharArray()[0]);

                if (right.charAt(0) == ch.toCharArray()[0]) {
                    Exp exp = new Exp(X);
                    exp.addRight(right);
                    return exp;
                }
            }
        }
        return null;//--------------------此处可抛出异常处理 之后再补--------------------------//

    }

    // next_char是下一个输入的字符 X是堆栈的栈顶的非终结符 vn是非终结符
    // 如果next_char在First(vn)中, 将X->vn加入到table[X,next_char]中 表示遇到next_char的时候可以选用X->vn这个产生式去推导
    // 如果vn是可为空的 那么对next_char属于Follow(X) 将X->vn加入到table[X,next_char]中
    public static void getTable(HashMap<Pair<String, String>, Exp> table, compliation_02 Test) {
        for (String vn : // 初始化table每一项的Exp 注意Exp的右部会自动分配空间但大小为0
                Test.VN) {
            for (String vt :
                    Test.VT) {
                Pair pair = new Pair(vn, vt);
                table.put(pair, new Exp(vn));
            }
        }
        for (String vn :
                Test.VN) {
            for (String vt :
                    Test.VT) {
                Exp exp = findFirst(vn, vt, Test.First, Test.expSet);
                if (exp != null) { // 不为空则说明找到
                    Pair<String, String> p = new Pair<>(vn, vt); // 构造key
                    table.put(p, exp); // 加入到table中
                }
            }
        }

    }

    public char getNext(int index, char[] list) {
        /**
         * 取出下一个带匹配的字符
         */
        return list[index];
    }

    public void pushToStack(Pair key) {
        /**
         * 用于analyze函数调用
         * 将table中key对应的产生式右部逆序入栈
         */
        Exp exp = table.get(key); // 取出产生式
        String temp = (String)exp.getRightList().get(0);
        char[] chars = temp.toCharArray(); // 转换为字符数组方便根据下标逆序
        for (int i = chars.length-1; i >= 0; i--) { // 逆序入栈
            s.push(String.valueOf(chars[i]));
        }
    }

    public void analyze(String startChar, compliation_02 Test) {
        /**
         * 对输入的串进行预测分析
         */
        //HashSet<String> VN = Test.VN;
        HashSet<String> VT = Test.VT;
        HashSet<String> VN = Test.VN;
        s.push(endChar); // 将#和开始符入栈
        s.push(startChar);

        int index = 0; // 记录input已经匹配字符的位置
        char[] list = input.toCharArray(); // 转换成字符数组
        char head;
        boolean flag = false;
        head = getNext(index, list);
        while (true) {
            String pop = s.pop();
            System.out.println("取出的栈顶元素为: "+pop);//-----------------------调试-----------------------------//
            System.out.println("当前输入字符为: "+head);//-----------------------调试-----------------------------//
            if (VT.contains(pop) || pop.equals(endChar)) { // 栈顶取出的是终结符或者"#"
                if (pop.toCharArray()[0] == head) { // 判断是否匹配
                    System.out.println(head+"和"+pop+"匹配了");
                    index++;
                    System.out.println("index:"+index);//-----------------------调试-----------------------------//
                    head = getNext(index, list);
                } else {
                    System.out.println("head和pop匹配失败"); //-------------------------失败---------------------------------
                    flag = true;
                    break;
                }
            } else { // 栈顶取出的是非终结符 或者"#" 那么继续根据table表选择产生式并加入栈中 注意字符入栈顺序
                if (pop.equals("#")) { // 如果当前栈顶元素是"#"
                    if(head == '#') { // 而且input的当前字符也是"#"
                        System.out.println("成功啦.-."); // input是正确的
                        break;
                    }
                } else { // 根据pop和head组成的key来查表进行产生式的选择
                    if (VN.contains(pop)) {  // 进行判断 以免抛出异常
                        Pair<String, String> key = new Pair<>(pop, String.valueOf(head)); // 构造key
                        if (table.containsKey(key)) { // 查表
                            System.out.println(s.peek() + "选择的产生式右部为: " + table.get(key).rightList.get(0));//---------------调试--------------------//
                            pushToStack(key); // 逆序入栈

                            // 调试输出栈
                            Iterator<String> iterator = s.iterator();
                            System.out.println("----------------------------------------");
                            while (iterator.hasNext()) {
                                System.out.println(iterator.next());
                            }
                            //
                        } else {
                            System.out.println("匹配失败, table中没有这个key"); //-------------------------失败---------------------------------
                            flag = true;
                            break;
                        }
                    }
                }
            }
            if (flag) { // flag为true说明某一步匹配失败
                break; // 跳出最外层while循环
            }
        }
//        if (!flag) {
//            System.out.println("成功啦.-.");
//        }
    }

    // 查找M[A,a]对应的产生式操作为 table.get()
    public static void main(String[] args) {
        compliation_02 Test = new data_case().analyzeData();
        GrammerTableAnalyzer analyzer = new GrammerTableAnalyzer("gdd");
        analyzer.getTable(analyzer.table, Test);
        System.out.println(analyzer.table.containsKey(new Pair<>("A", "g")));
//        for (Pair p :
//                analyzer.table.keySet()) {
//            System.out.println("key: " + p);
//            System.out.println("value: " + analyzer.table.get(p).left + "->" + analyzer.table.get(p).rightList);
//        }
//        for (String vn :
//                Test.VN) {
//            System.out.println("vn: " + vn + ":");
//            HashSet<String> exps = Test.expSet.get(vn);
//            for (String exp :
//                    exps) {
//                System.out.println(exp);
//            }
//            System.out.println("----------");
//        }

        analyzer.analyze("A",Test);
    }
}
