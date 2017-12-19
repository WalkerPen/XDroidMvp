package com.example.demo.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * Created by Pen on 2017/9/22.
 */
@XStreamAlias("user")
public class User {
    /**
     * name : 彭良辉
     * gender : 男
     * age : 25
     * wifes : ["刘亦菲","刘诗诗","杨幂"]
     * address : {"province":"湖南","city":"临湘","town":"定湖"}
     */

    public String name;
    public String gender;
    public String age;
    public AddressBean address;
    public List<String> wifes;

    @XStreamAlias("address")
    public static class AddressBean {
        /**
         * province : 湖南
         * city : 临湘
         * town : 定湖
         */

        public String province;
        public String city;
        public String town;
    }
}
