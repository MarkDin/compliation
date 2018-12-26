package LR文法分析;

class Item {
    String left;
    String right; // 右部的符号
    int index; // 圆点的位置

    public Item(String left, String right, int index) {
        /*
        初始化需要两个参数 需给定圆点位置
         */
        this.left = left;
        this.right = right;
        this.index = index;
    }

    public Item(String left, String right) {
        /*
        下标index默认初始化为0 即圆点最开始在最左边
         */
        this.left = left;
        this.right = right;
        this.IniIndex();
    }

    @Override
    public int hashCode() {
        return left.hashCode() + right.hashCode() + (index + 1) * 1000;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item item = (Item)obj;
            return (left.equals(item.left) && right.equals(item.right) && index == item.index);
        }
        return super.equals(obj);
    }

    public void setLeft(String left) {
        /*
        设置left
         */
        this.left = left;
    }

    public int length() {
        /*
        返回字符的长度
         */
        return right.length();
    }

    public int getIndex() {
        /*
        返回圆点的位置
         */
        return index;
    }

    /**
     * 取出圆点后面的第一个字符
     * @param
     * @return 圆点后面的第一个字符
     * @Exception
     */
    public String getCharBehindDot() {

        return String.valueOf(this.right.charAt(this.index));
    }

    /**
     *@description TODO
    []
     *@return  void
     *@Exception
     *@创建人  DingKe
     *@创建时间  9:39 2018/12/26
     */
    public void IniIndex() {

        this.index = 0;
    }

    @Override
    public String toString() {
        String Right = new String();
        try { // 检查index范围
            if (index > this.right.length()) {
                throw new Exception("圆点位置超出长度范围");
            } else {
                if (this.index == 0) {
                    return this.left + "-->" + "." + right;
                }
                String A = this.right.substring(0, index);
                String B = this.right.substring(index, this.right.length());

                Right = A + "." + B;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.left + "-->" + Right;

    }
}
