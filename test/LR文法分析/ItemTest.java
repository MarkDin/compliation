/*
用于测试Item的toString方法
 */
package LR文法分析;

import LR文法分析.Item;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

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