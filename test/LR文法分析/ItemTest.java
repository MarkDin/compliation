/*
用于测试Item的toString方法
 */
package LR文法分析;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class ItemTest {
    static String res = new String();
    @BeforeEach
    void Init() {
        res = new Item("S", "A", 1).toString();
    }
    @Test
    public  void toStringTest() {
        assertNotNull(res);
        assertEquals("S-->A.", res);
    }
}