package com.example.demo.net.bean;

import java.util.List;

/**
 * Created by Pen on 2017/7/27.
 */

public class CityListResponse {
    /**
     * code : 0
     * msg : success
     * data : [{"id":"2","name":"辽宁省","child":[{"id":"3","title":"大连市"},{"id":"17","title":"庄河市"},{"id":"944","title":"营口市"},{"id":"952","title":"盖州市"},{"id":"965","title":"瓦房店市"},{"id":"970","title":"普兰店市"}]},{"id":"52","name":"江苏省","child":[{"id":"53","title":"泰州市"},{"id":"60","title":"扬州市"},{"id":"741","title":"南京市"}]},{"id":"65","name":"浙江省","child":[{"id":"66","title":"义乌市"},{"id":"397","title":"杭州市"},{"id":"1016","title":"衢州市"},{"id":"1061","title":"宁波市"},{"id":"1146","title":"台州市"},{"id":"1162","title":"温州市"},{"id":"1250","title":"绍兴市"},{"id":"1304","title":"嘉兴市"},{"id":"1313","title":"金华市"},{"id":"1438","title":"丽水市"}]},{"id":"111","name":"山东省","child":[{"id":"112","title":"济南市"},{"id":"119","title":"烟台市"},{"id":"124","title":"泰安市"},{"id":"132","title":"潍坊市"},{"id":"137","title":"青岛市"},{"id":"154","title":"青州市"},{"id":"159","title":"昌乐市"},{"id":"832","title":"日照市"},{"id":"169","title":"枣庄市"},{"id":"174","title":"滕州市"},{"id":"179","title":"曲阜市"},{"id":"183","title":"淄博市"},{"id":"195","title":"即墨市"},{"id":"204","title":"莱西市"},{"id":"209","title":"莱阳市"},{"id":"216","title":"栖霞市"},{"id":"220","title":"海阳市"},{"id":"231","title":"威海市"},{"id":"243","title":"荣城市"},{"id":"249","title":"文登市"},{"id":"1071","title":"济宁市"},{"id":"1176","title":"莱芜市"}]},{"id":"295","name":"山西省","child":[{"id":"861","title":"朔州市"},{"id":"296","title":"晋中市"},{"id":"325","title":"太原市"},{"id":"342","title":"大同市"},{"id":"351","title":"吕梁市"},{"id":"722","title":"运城市"},{"id":"975","title":"临汾市"}]},{"id":"357","name":"内蒙古自治区","child":[{"id":"358","title":"鄂尔多斯市"},{"id":"1195","title":"包头市"}]},{"id":"392","name":"上海","child":[{"id":"393","title":"上海市"}]}]
     */

    public int code;
    public String msg;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 2
         * name : 辽宁省
         * child : [{"id":"3","title":"大连市"},{"id":"17","title":"庄河市"},{"id":"944","title":"营口市"},{"id":"952","title":"盖州市"},{"id":"965","title":"瓦房店市"},{"id":"970","title":"普兰店市"}]
         */

        public String id;
        public String name;
        public List<ChildBean> child;

        public static class ChildBean {
            /**
             * id : 3
             * title : 大连市
             */

            public String id;
            public String title;
        }
    }
}
