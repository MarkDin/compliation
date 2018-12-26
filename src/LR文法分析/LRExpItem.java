package LR文法分析;
import code.Exp;

import java.util.ArrayList;

public class LRExpItem extends Exp{

    public LRExpItem(String left, Item item) {
        this.left = left;
        this.rightList = new ArrayList<Item>();
        this.rightList.add(item);
    }

    public LRExpItem(String left) {
        this.rightList = new ArrayList<Item>();
        this.left = left;
    }



    //Pair<String, Integer> [] right;
    public void setLeft(String left) {
        /**
         * 设置一个非终结符的串作为左部
         */
        this.left = left;
    }


    public void addRight(Item item) {
        /**
         * 添加一个做部分为left对应的right
         */
        rightList.add(item);

    }

    public ArrayList getRightList(){
        return rightList;
    }
}


