package LR文法分析;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

public class LR0Test {
    LR0Grammar grammar = new LR0Grammar();
    HashSet<Item> itemHashSet = new HashSet<>();
    HashMap<String, HashSet<String>> exps = new HashMap<String, HashSet<String>>();
    HashSet<String> temp = new HashSet<>();

    @Before
    public void InitData() {
        // 构造产生式
        // SS-->S
        temp.add("S");
        exps.put("SS", temp);
        temp = new HashSet<>();
        // S-->AaBc
        temp.add("AaBc");
        exps.put("S", temp);
        temp = new HashSet<>();
        // A-->Bc A-->a
        temp.add("Bc");
        temp.add("a");
        exps.put("A", temp);
        temp = new HashSet<>();
        // B-->c
        temp.add("c");
        exps.put("B", temp);
        // 初始化item集合 把项目加入其中
        itemHashSet.add(new Item("SS", "S", 0));
        // 构造VN集合
        HashSet<String> VN = new HashSet<>();
        VN.add("SS");
        VN.add("S");
        VN.add("A");
        VN.add("B");
        grammar.expSet = exps;
        grammar.VN = VN;
    }

    @Test
    public void closure() {
//        closure()函数只需两个参数:表达式集合, item集合
        grammar.closure(itemHashSet);
    }

    @Test
    public void _goto() {
        // 构造产生式
        // SS-->S
        temp.add("S");
        exps.put("SS", temp);
        temp = new HashSet<>();
        // S-->AaBc
        temp.add("aBc");
        exps.put("S", temp);
        temp = new HashSet<>();
        // A-->Bc A-->a
        temp.add("Bc");
        temp.add("a");
        exps.put("A", temp);
        temp = new HashSet<>();
        // B-->c
        temp.add("c");
        exps.put("B", temp);
        Item item = new Item("df", "df", 3);
        HashSet<Item> set = grammar._goto(itemHashSet, "a");
        assertNotNull(itemHashSet);
    }
}