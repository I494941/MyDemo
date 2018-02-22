package com.example.a94941.mydemo;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

/**
 * @创建者 94941
 * @创建时间 2018/2/22
 * @描述 ${TODO}
 */
public class ListTest {

    @Test
    public void testGet() throws Exception {

        // 创建mock对象
        List mockedList = Mockito.mock(List.class);

        // 设置mock对象的行为 － 当调用其get方法获取第0个元素时，返回"one"
        Mockito.when(mockedList.get(0)).thenReturn("one");

        // 使用mock对象 － 会返回前面设置好的值"one"，即便列表实际上是空的
        String str = (String) mockedList.get(0);
        Assert.assertTrue("one".equals(str));
        Assert.assertTrue(mockedList.size() == 0);

        // 验证mock对象的get方法被调用过，而且调用时传的参数是0
        Mockito.verify(mockedList).get(0);
    }
}