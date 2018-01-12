package com.larry.util;

import java.util.UUID;

public class UUIds {

    // TODO: 2018/1/8 生成32为的id
    public static String getId32(){
        String id32 = UUID.randomUUID().toString().replaceAll("-", "");
//        System.out.println(id32);
        return id32;
    }

    // TODO: 2018/1/8 生成64位id

    public  static String getId64(){
        String id64 = "";
        for (int i = 0; i <2 ; i++) {
            id64+=getId32();
        }
        return id64;
    }

}
