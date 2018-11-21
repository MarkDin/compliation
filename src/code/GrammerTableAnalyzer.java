/**
 * 自顶向下分析方法的表驱动分析程序(语法表)
 */
package code;

import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Stack;

import code.compliation_02.*;
import code.data_case.*;
import org.junit.Test;

public class GrammerTableAnalyzer {
    Stack<String> s; //堆栈
    Stack<String> input;// 输入串
    HashMap< Pair<String,String>, Exp> table;// 预测分析表 key是非终结符和终结符组成 value是对应的表达式
    data_case Test = new data_case();//////////-----------------------------测试用----------------------------

    public GrammerTableAnalyzer(Stack<String> s, Stack<String> input, HashMap<Pair<String, String>, Exp> table) {
        this.s = s;
        this.input = input;
        this.table = table;
        Exp res = table.get(new Pair("A", "a"));
        System.out.println(res.left);
    }

    public Exp findFirst(String X, String ch) {
        /**
         * 用于getTable函数调用
         * 来查找非终结符X的First集中能推出ch的产生式
         * 并返回这个产生式
         */
        HashMap<String, HashSet<String>> First = Test.nullable().First;
        if (First.containsKey(X) && First.get(X).contains(ch)) { // 判断ch是否存在于X的First集中
            for (String right :
                    Test.nullable().expSet.get(X)) { // 遍历X的全部产生式
                if (right.charAt(0) == ch.toCharArray()[0]) {
                    System.out.println("产生式右部第一个符号为"+right.charAt(0));//----------------------------------
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
    public void getTable(){
        for (String vn : // 初始化table每一项的Exp 注意Exp的右部会自动分配空间但大小为0
                Test.nullable().VN) {
            for (String vt :
                    Test.nullable().VT) {
                Pair pair = new Pair(vn, vt);
                table.put(pair, new Exp(vn));
            }
        }
        for (String vn :
                Test.nullable().VN) {
            for (String vt :
                    Test.nullable().VT) {
                Exp exp = findFirst(vn, vt);
                if ( exp != null) { // 不为空则说明找到
                    Pair<String, String> p = new Pair<>(vn,vt); // 构造key
                    table.put(p, exp); // 加入到table中
                }
            }
        }

    }
    //</editor-fold>
    // 查找M[A,a]对应的产生式操作为 table.get()
    public static void main(String[] args) {
        Stack<String> s = new Stack<>();
        Stack<String> input = new Stack<>();// 输入串
        HashMap< Pair<String,String>, Exp>  table = new HashMap<>();
        table.put(new Pair<>("A", "a"), new Exp("A"));
        new GrammerTableAnalyzer(s, input , table);
    }

}
