package code;

import java.util.ArrayList;

public class LLExp extends Exp{
    String left;
    ArrayList<String> rightList;

    public LLExp(String left) {
        this.left = left;
        rightList = new ArrayList<String>();
    }

    public LLExp(String left, String right) {
        this.left = left;
        this.rightList = new ArrayList<String>();
        this.rightList.add(right);
    }

    public void setLeft(String left) {
        /**
         * 设置一个非终结符的串作为左部
         */
        this.left = left;
    }

    
    public void addRight(String right) {
        /**
         * 添加一个做部分为left对应的right
         */
        rightList.add(right);
    }

    public ArrayList getRightList(){
        return rightList;
    }

}
