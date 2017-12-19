package com.example.demo.net.bean;

/**
 * Created by Pen on 2017/7/27.
 */

public class LoginResponse {
    /**
     * code : 0
     * msg : success
     * data : {"data":{"phone":"176****7562","nickname":"彭良辉","role":3,"province":"辽宁省","city":"大连市","id_card":"430***********5310"}}
     */

    public int code;
    public String msg;
    public DataBeanX data;

    public static class DataBeanX {
        /**
         * data : {"phone":"176****7562","nickname":"彭良辉","role":3,"province":"辽宁省","city":"大连市","id_card":"430***********5310"}
         */

        public DataBean data;

        public static class DataBean {
            /**
             * phone : 176****7562
             * nickname : 彭良辉
             * role : 3
             * province : 辽宁省
             * city : 大连市
             * id_card : 430***********5310
             */

            public String phone;
            public String nickname;
            public int role;
            public String province;
            public String city;
            public String id_card;
        }
    }
}
