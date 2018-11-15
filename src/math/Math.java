package math;

public class Math {
    public int fac(int n) throws Exception{
        if (n < 0) {
            throw new Exception("负数没有阶乘");
        } else if (n <= 1) {
            return 1;
        } else {
            return n * fac(n-1);
        }
    }

    public static void main(String[] args) throws Exception {
        int fac = new Math().fac(5);
        System.out.println(fac);
    }
}
