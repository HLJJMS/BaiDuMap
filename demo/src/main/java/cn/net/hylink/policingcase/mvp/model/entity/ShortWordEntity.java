package cn.net.hylink.policingcase.mvp.model.entity;

import java.util.List;

public class ShortWordEntity {

    /**
     * reason : null
     * result : {"data":[{"code":"3050010101","level":3,"children":[],"name":"无效报警","pid":"3d8b3d38-cd36-4103-976d-092259bb6e21","remark":"","id":"5fd30cd5-749c-41c8-bcd2-0b6496279876","sort":1,"appCode":"106305","value":""},{"code":"3050010102","level":3,"children":[],"name":"纠纷已协调完成","pid":"3d8b3d38-cd36-4103-976d-092259bb6e21","remark":"","id":"fdc0dfa3-782e-420f-a31a-bab68f84ffba","sort":1,"appCode":"106305","value":""},{"code":"3050010103","level":3,"children":[],"name":"已转交其他部门","pid":"3d8b3d38-cd36-4103-976d-092259bb6e21","remark":"","id":"f54ec2a5-0bff-4b18-9c6c-5110eb4286b6","sort":1,"appCode":"106305","value":""}]}
     */


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * code : 3050010101
         * level : 3
         * children : []
         * name : 无效报警
         * pid : 3d8b3d38-cd36-4103-976d-092259bb6e21
         * remark :
         * id : 5fd30cd5-749c-41c8-bcd2-0b6496279876
         * sort : 1
         * appCode : 106305
         * value :
         */

        private String code;
        private int level;
        private String name;
        private String pid;
        private String remark;
        private String id;
        private int sort;
        private String appCode;
        private String value;
        private List<?> children;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getAppCode() {
            return appCode;
        }

        public void setAppCode(String appCode) {
            this.appCode = appCode;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }
    }
}

