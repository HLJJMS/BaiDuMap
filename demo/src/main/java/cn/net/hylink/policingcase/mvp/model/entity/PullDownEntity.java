package cn.net.hylink.policingcase.mvp.model.entity;

import java.util.List;

public class PullDownEntity {

    /**
     * reason : null
     * result : {"list":[{"dictvalue":"行政（治安）警情","dictkey":"J020000"},{"dictvalue":"群体性事件","dictkey":"J030000"},{"dictvalue":"交通警情","dictkey":"J040000"},{"dictvalue":"消防警情","dictkey":"J050000"},{"dictvalue":"求助","dictkey":"J060000"},{"dictvalue":"举报","dictkey":"J070000"},{"dictvalue":"投诉监督","dictkey":"J080000"},{"dictvalue":"失踪人员","dictkey":"J090000"},{"dictvalue":"意外死亡","dictkey":"J100000"},{"dictvalue":"其他灾害事故","dictkey":"J110000"},{"dictvalue":"预警指令","dictkey":"J120000"},{"dictvalue":"便民服务","dictkey":"J130000"},{"dictvalue":"社会联动","dictkey":"J140000"},{"dictvalue":"其他","dictkey":"J990000"}]}
     */


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * dictvalue : 行政（治安）警情
         * dictkey : J020000
         */

        private String dictvalue;
        private String dictkey;

        public String getDictvalue() {
            return dictvalue;
        }

        public void setDictvalue(String dictvalue) {
            this.dictvalue = dictvalue;
        }

        public String getDictkey() {
            return dictkey;
        }

        public void setDictkey(String dictkey) {
            this.dictkey = dictkey;
        }
    }
}

