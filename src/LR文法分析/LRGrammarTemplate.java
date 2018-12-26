/*
定义LR文法的接口 用于拓展SLR LR(k)文法
 */
package LR文法分析;

import java.util.HashSet;

public interface LRGrammarTemplate {
    /*
    closure闭包函数
    ACTION函数
    _goto函数 求单个项目集遇到文法符号a时的新状态
    GOTO函数
    总控函数
     */

    public void closure(HashSet<Item> I);

    public void action();

    public HashSet<Item> _goto(HashSet<Item> items, String a);

    public void Goto(); // 求整个分析过程的GOTO表

    public void cotrol();

}
