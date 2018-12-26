package code;

import org.junit.Test;

public class compliation_02_nullable_Test {

    @Test
    public void nullable() {
        LLGrammar test = new data_case().nullable();
        test.Init();
        for (String n :
                test.expSet.keySet()) {
            System.out.println(n+":"+test.expSet.get(n));
        }
        test.nullable();
        for (String vn :
                test.VN) {
            System.out.println("vn:"+vn+"--"+test.Nullable.get(vn));
        }

    }
}