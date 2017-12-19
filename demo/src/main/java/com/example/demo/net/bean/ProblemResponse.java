package com.example.demo.net.bean;

import java.util.List;

/**
 * Created by Pen on 2017/7/27.
 */

public class ProblemResponse {

    /**
     * code : 0
     * msg : 获取成功
     * data : {"data":[{"title":"U盘常见问题处理","id":"176","url":"http://menshen.datuhongan.com/ops_task_manual/detail?id=176"},{"title":"设置静态IP的流程","id":"175","url":"http://menshen.datuhongan.com/ops_task_manual/detail?id=175"},{"title":"路由器检测流程及常见问题处理方式","id":"173","url":"http://menshen.datuhongan.com/ops_task_manual/detail?id=173"},{"title":"路由器升级步骤","id":"172","url":"http://menshen.datuhongan.com/ops_task_manual/detail?id=172"},{"title":"路由器升级注意事项","id":"171","url":"http://menshen.datuhongan.com/ops_task_manual/detail?id=171"},{"title":"关于方圆系统、新API系统、方圆移动版上线前的提醒","id":"170","url":"http://menshen.datuhongan.com/ops_task_manual/detail?id=170"},{"title":"屏幕出现--设备维护中字样","id":"168","url":"http://menshen.datuhongan.com/ops_task_manual/detail?id=168"},{"title":"出现拷贝资源失败","id":"119","url":"http://menshen.datuhongan.com/ops_task_manual/detail?id=119"},{"title":"无权更新终端","id":"118","url":"http://menshen.datuhongan.com/ops_task_manual/detail?id=118"},{"title":"解压失败的提示","id":"117","url":"http://menshen.datuhongan.com/ops_task_manual/detail?id=117"},{"title":"用户登录之后提示ID有误","id":"116","url":"http://menshen.datuhongan.com/ops_task_manual/detail?id=116"},{"title":"插入刊例/展示系统U盘后没有弹出用户登录框","id":"115","url":"http://menshen.datuhongan.com/ops_task_manual/detail?id=115"}]}
     */

    public int code;
    public String msg;
    public DataBeanX data;

    public static class DataBeanX {
        public List<DataBean> data;

        public static class DataBean {
            /**
             * title : U盘常见问题处理
             * id : 176
             * url : http://menshen.datuhongan.com/ops_task_manual/detail?id=176
             */

            public String title;
            public String id;
            public String url;
        }
    }
}
