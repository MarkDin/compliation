package code;

import java.util.ArrayList;

public abstract class Exp {

    public ArrayList rightList;
    public String left;

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
    }

    public ArrayList getRightList(){
        return null;
    }
}
