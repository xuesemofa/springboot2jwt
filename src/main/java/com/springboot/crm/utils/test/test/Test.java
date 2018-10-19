package com.springboot.crm.utils.test.test;

import com.springboot.crm.business.customer.model.CustomerModel;

import java.lang.reflect.Field;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class Test {

    public static void main(String[] args) {
        Field[] fields = CustomerModel.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
//            设置私有属性允许访问
            fields[i].setAccessible(true);
            System.out.println(fields[i].getName() + i);
        }
    }
}
