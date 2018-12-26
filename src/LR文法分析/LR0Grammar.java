package LR文法分析;

import code.Grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
/**
 *@description 每个产生式需要标号 因为在进行归约操作的时候 标号可以指示使用哪一条产生式
 *@创建人  DingKe
 *@创建时间  10:40 2018/12/26
 */
public class LR0Grammar extends Grammar implements LRGrammarTemplate {

    // S -->aGAc 点的位置个数等于右部符号长度+1 下标为0-->len

    HashMap<String, HashSet<LRExpItem>> expItemSet; // 定义项目集合

    //    构造函数
    public LR0Grammar() {
        // super();
        //增广文法
    }

    public LR0Grammar(String S, HashSet<String> VT, HashSet<String> VN, HashMap<String,
            HashSet<String>> expSet) {
        this.S = S;
        this.VT = VT;
        this.VN = VN;
        this.expSet = expSet;
    }

    /**

     *@描述

     *@参数  [left, right]

     *@返回值  LR文法分析.Item

     *@创建人  DingKe

     *@创建时间  2018/12/26

     */
    public Item ExpToItem(String left, String right) {
        Item item = new Item(left, right, 0);
        return item;
    }

    public ArrayList<Item> ExpsToItems(String left, HashSet<String> set) {
        /*
        输入为 left:表达式左部字符串 set:left对应的表达式set集合
        输出为 将字符串类型的文法表达式集合转换成带圆点的项目集合 并返回
         */
        ArrayList<Item> itemArrayList = new ArrayList<>();
        for (String string :
                set) {
            Item item = new Item(left, string, 0); // 所以新加入项目圆点都在最前面 index应该为0
            itemArrayList.add(item);
        }

        return itemArrayList;
    }

    public void InitBookMap(HashMap<Item, Boolean> book, HashSet<Item> items) {
        /*
        将items所有item的book标记初始化为false 表示未使用
         */
        for (Item item : items) {
            book.put(item, false);
        }
    }

    @Override
    public void Init() {

    }

    @Override
    /**
     *@description TODO
    [items]
     *@return  void
     *@Exception
     *@创建人  DingKe
     *@创建时间  9:45 2018/12/26
     */
    public void closure(HashSet<Item> items) {
        /*
        输入为item的HashSet
items.addAll(ExpsToItems(left, expLRHashSet));迭代时修改会抛出java.util.ConcurrentModificationException异常
         */
        boolean flag; // 标记是否还存在未加入的项目集
        HashMap<Item, Boolean> book = new HashMap<>();// 用于标记项目是否已经求闭包
        InitBookMap(book, items); // 将items所以item的book标记初始化为false 表示未使用

        while (true) {
            flag = false;
            HashSet<Item> temp = new HashSet<Item>(); // 临时保存下面要增加的项目
            for (Item item : items) { //取出项目集的项目
                String left = item.left;
                String ch = String.valueOf(item.right.charAt(item.index)); // 取出圆点后面的第一个字符
                System.out.println("1打印item: " + item);/////////////////
                if (this.VN.contains(ch) && book.get(item) == false) { // 如果圆点后面是非终结符
                    book.replace(item, true); // 改变book标记
                    HashSet<String> expLRHashSet = this.expSet.get(ch); // 取出ch的产生式set集合
                    temp.addAll(ExpsToItems(ch, expLRHashSet)); // 暂时保存在temp中
                    flag = true;
                    System.out.println("2打印增添的项目集: ");
                    for (Item item1 : temp) {
                        System.out.println(item1);///////////////
                    }
                }
            }
            items.addAll(temp); // 将临时保存的放入items
            InitBookMap(book, temp); // 将新加入的项目的book标记初始化为false
            System.out.println("3打印items: ");
            for (Item item2 : items) {
                System.out.println(item2);
            }
            System.out.println("------------------------");
            if (!flag) { // 已经全部求出
                break; // 退出循环
            }
        }//
    }

    @Override
    public void action() {

    }

    @Override
    /**
     * 求单个转态的新的转换状态
     * @param [items]
     * @return 圆点后面的第一个字符
     * @Exception
     */
    public HashSet<Item> _goto(HashSet<Item> items, String a) {
        HashSet<Item> newItems = new HashSet<>();
        for (Item item : items) {
            String ch = item.getCharBehindDot(); // 取出圆点后面的第一个字符
            if (ch.equals(a)) { // 和参数a相等
                newItems.add(new Item(item.left, item.right, item.index + 1)); // 构建当前item的下一个转态
            }
        }
        return newItems;
    }

    @Override
    public void Goto() {
    }

    @Override
    public void cotrol() {

    }

    public static void main(String[] args) {

    }
}




