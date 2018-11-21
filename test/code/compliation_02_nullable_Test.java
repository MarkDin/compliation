package code;

import com.sun.istack.internal.Nullable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

public class compliation_02_nullable_Test {

    @Test
    public void nullable() {
        compliation_02 test = new data_case().nullable();
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