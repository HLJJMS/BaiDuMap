package cn.net.hylink.policingcase.mvp.model.entity;

public class JqCountEntity {


    /**
     * data : {"bzjqs":0,"bsjqs":10}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bzjqs : 0
         * bsjqs : 10
         */

        private int bzjqs;
        private int bsjqs;
        private int jq01;
        private int jq02;
        private int jq03;
        private int jq04;

        public int getJq01() {
            return jq01;
        }

        public void setJq01(int jq01) {
            this.jq01 = jq01;
        }

        public int getJq02() {
            return jq02;
        }

        public void setJq02(int jq02) {
            this.jq02 = jq02;
        }

        public int getJq03() {
            return jq03;
        }

        public void setJq03(int jq03) {
            this.jq03 = jq03;
        }

        public int getJq04() {
            return jq04;
        }

        public void setJq04(int jq04) {
            this.jq04 = jq04;
        }

        public int getBzjqs() {
            return bzjqs;
        }

        public void setBzjqs(int bzjqs) {
            this.bzjqs = bzjqs;
        }

        public int getBsjqs() {
            return bsjqs;
        }

        public void setBsjqs(int bsjqs) {
            this.bsjqs = bsjqs;
        }
    }
}
