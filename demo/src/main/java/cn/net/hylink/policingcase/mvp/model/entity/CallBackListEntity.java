package cn.net.hylink.policingcase.mvp.model.entity;

import java.util.List;

public class CallBackListEntity {

    /**
     * reason : null
     * result : {"list":[{"xh":1,"jqbh":"53250020190723143521009","BJR":"刘先生","ajfssj_y":"2019/9/16 14:35:21","ajjssj_y":"2020-03-20 01:19:06","BJDH":"18217351334","cdccs":"1","cdrs":"2","fkrxm":"车载测试","jqfklbmc":"求助","jqfklxmc":"生活噪音","jqfkxlmc":"","ajfssj":"2610-02-15 08:00:00","ajjssj":"2020-03-20 01:19:09","cjqk":"222","fsdd":"昌平路","fksj":"2020-03-20 01:19:09","ssrs":"0","zhrs":"0","swrs":"0"},{"xh":2,"jqbh":"53250020190723143521009","BJR":"刘先生","ajfssj_y":"2019/9/16 14:35:21","ajjssj_y":"2020-03-18 01:56:37","BJDH":"18217351334","cdccs":"1","cdrs":"2","fkrxm":"张永卓","jqfklbmc":"求助","jqfklxmc":"生活噪音","jqfkxlmc":"","ajfssj":"2610-02-15 07:26:40","ajjssj":"2020-03-18 02:00:42","cjqk":"112","fsdd":"昌平路","fksj":"2020-03-18 02:00:42","ssrs":"0","zhrs":"0","swrs":"0"},{"xh":3,"jqbh":"53250020190723143521009","BJR":"刘先生","ajfssj_y":"2019/9/16 14:35:21","ajjssj_y":"2020-03-17 08:24:39","BJDH":"18217351334","cdccs":"1","cdrs":"2","fkrxm":"车载测试","jqfklbmc":"求助","jqfklxmc":"生活噪音","jqfkxlmc":"","ajfssj":"2610-02-15 07:10:00","ajjssj":"2020-03-17 08:24:52","cjqk":"霊u","fsdd":"昌平路","fksj":"2020-03-17 08:24:52","ssrs":"0","zhrs":"0","swrs":"0"}],"page":{"showCount":999,"totalPage":0,"totalResult":0,"currentPage":0,"currentResult":0,"entityOrField":false,"pageStr":"","pd":{"jqbh":"53250020190723143521009","imei":"tempHylinkCid","jjdzt":"05"}}}
     */

    /**
     * list : [{"xh":1,"jqbh":"53250020190723143521009","BJR":"刘先生","ajfssj_y":"2019/9/16 14:35:21","ajjssj_y":"2020-03-20 01:19:06","BJDH":"18217351334","cdccs":"1","cdrs":"2","fkrxm":"车载测试","jqfklbmc":"求助","jqfklxmc":"生活噪音","jqfkxlmc":"","ajfssj":"2610-02-15 08:00:00","ajjssj":"2020-03-20 01:19:09","cjqk":"222","fsdd":"昌平路","fksj":"2020-03-20 01:19:09","ssrs":"0","zhrs":"0","swrs":"0"},{"xh":2,"jqbh":"53250020190723143521009","BJR":"刘先生","ajfssj_y":"2019/9/16 14:35:21","ajjssj_y":"2020-03-18 01:56:37","BJDH":"18217351334","cdccs":"1","cdrs":"2","fkrxm":"张永卓","jqfklbmc":"求助","jqfklxmc":"生活噪音","jqfkxlmc":"","ajfssj":"2610-02-15 07:26:40","ajjssj":"2020-03-18 02:00:42","cjqk":"112","fsdd":"昌平路","fksj":"2020-03-18 02:00:42","ssrs":"0","zhrs":"0","swrs":"0"},{"xh":3,"jqbh":"53250020190723143521009","BJR":"刘先生","ajfssj_y":"2019/9/16 14:35:21","ajjssj_y":"2020-03-17 08:24:39","BJDH":"18217351334","cdccs":"1","cdrs":"2","fkrxm":"车载测试","jqfklbmc":"求助","jqfklxmc":"生活噪音","jqfkxlmc":"","ajfssj":"2610-02-15 07:10:00","ajjssj":"2020-03-17 08:24:52","cjqk":"霊u","fsdd":"昌平路","fksj":"2020-03-17 08:24:52","ssrs":"0","zhrs":"0","swrs":"0"}]
     * page : {"showCount":999,"totalPage":0,"totalResult":0,"currentPage":0,"currentResult":0,"entityOrField":false,"pageStr":"","pd":{"jqbh":"53250020190723143521009","imei":"tempHylinkCid","jjdzt":"05"}}
     */

    private PageBean page;
    private List<ListBean> list;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class PageBean {
        /**
         * showCount : 999
         * totalPage : 0
         * totalResult : 0
         * currentPage : 0
         * currentResult : 0
         * entityOrField : false
         * pageStr :
         * pd : {"jqbh":"53250020190723143521009","imei":"tempHylinkCid","jjdzt":"05"}
         */

        private int showCount;
        private int totalPage;
        private int totalResult;
        private int currentPage;
        private int currentResult;
        private boolean entityOrField;
        private String pageStr;
        private PdBean pd;

        public int getShowCount() {
            return showCount;
        }

        public void setShowCount(int showCount) {
            this.showCount = showCount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalResult() {
            return totalResult;
        }

        public void setTotalResult(int totalResult) {
            this.totalResult = totalResult;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getCurrentResult() {
            return currentResult;
        }

        public void setCurrentResult(int currentResult) {
            this.currentResult = currentResult;
        }

        public boolean isEntityOrField() {
            return entityOrField;
        }

        public void setEntityOrField(boolean entityOrField) {
            this.entityOrField = entityOrField;
        }

        public String getPageStr() {
            return pageStr;
        }

        public void setPageStr(String pageStr) {
            this.pageStr = pageStr;
        }

        public PdBean getPd() {
            return pd;
        }

        public void setPd(PdBean pd) {
            this.pd = pd;
        }

        public static class PdBean {
            /**
             * jqbh : 53250020190723143521009
             * imei : tempHylinkCid
             * jjdzt : 05
             */

            private String jqbh;
            private String imei;
            private String jjdzt;

            public String getJqbh() {
                return jqbh;
            }

            public void setJqbh(String jqbh) {
                this.jqbh = jqbh;
            }

            public String getImei() {
                return imei;
            }

            public void setImei(String imei) {
                this.imei = imei;
            }

            public String getJjdzt() {
                return jjdzt;
            }

            public void setJjdzt(String jjdzt) {
                this.jjdzt = jjdzt;
            }
        }
    }

    public static class ListBean {
        /**
         * xh : 1
         * jqbh : 53250020190723143521009
         * BJR : 刘先生
         * ajfssj_y : 2019/9/16 14:35:21
         * ajjssj_y : 2020-03-20 01:19:06
         * BJDH : 18217351334
         * cdccs : 1
         * cdrs : 2
         * fkrxm : 车载测试
         * jqfklbmc : 求助
         * jqfklxmc : 生活噪音
         * jqfkxlmc :
         * ajfssj : 2610-02-15 08:00:00
         * ajjssj : 2020-03-20 01:19:09
         * cjqk : 222
         * fsdd : 昌平路
         * fksj : 2020-03-20 01:19:09
         * ssrs : 0
         * zhrs : 0
         * swrs : 0
         */


        private List<String> xctp;
        private List<String> xyrtp;
        private List<String> qttp;
        private int xh;
        private String jqbh;
        private String BJR;
        private String ajfssj_y;
        private String ajjssj_y;
        private String BJDH;
        private String cdccs;
        private String cdrs;
        private String fkrxm;
        private String jqfklbmc;
        private String jqfklxmc;
        private String jqfkxlmc;
        private String ajfssj;
        private String ajjssj;
        private String cjqk;
        private String fsdd;
        private String fksj;
        private String ssrs;
        private String zhrs;
        private String swrs;

        public List<String> getXctp() {
            return xctp;
        }

        public void setXctp(List<String> xctp) {
            this.xctp = xctp;
        }

        public List<String> getXyrtp() {
            return xyrtp;
        }

        public void setXyrtp(List<String> xyrtp) {
            this.xyrtp = xyrtp;
        }

        public List<String> getQttp() {
            return qttp;
        }

        public void setQttp(List<String> qttp) {
            this.qttp = qttp;
        }

        public int getXh() {
            return xh;
        }

        public void setXh(int xh) {
            this.xh = xh;
        }

        public String getJqbh() {
            return jqbh;
        }

        public void setJqbh(String jqbh) {
            this.jqbh = jqbh;
        }

        public String getBJR() {
            return BJR;
        }

        public void setBJR(String BJR) {
            this.BJR = BJR;
        }

        public String getAjfssj_y() {
            return ajfssj_y;
        }

        public void setAjfssj_y(String ajfssj_y) {
            this.ajfssj_y = ajfssj_y;
        }

        public String getAjjssj_y() {
            return ajjssj_y;
        }

        public void setAjjssj_y(String ajjssj_y) {
            this.ajjssj_y = ajjssj_y;
        }

        public String getBJDH() {
            return BJDH;
        }

        public void setBJDH(String BJDH) {
            this.BJDH = BJDH;
        }

        public String getCdccs() {
            return cdccs;
        }

        public void setCdccs(String cdccs) {
            this.cdccs = cdccs;
        }

        public String getCdrs() {
            return cdrs;
        }

        public void setCdrs(String cdrs) {
            this.cdrs = cdrs;
        }

        public String getFkrxm() {
            return fkrxm;
        }

        public void setFkrxm(String fkrxm) {
            this.fkrxm = fkrxm;
        }

        public String getJqfklbmc() {
            return jqfklbmc;
        }

        public void setJqfklbmc(String jqfklbmc) {
            this.jqfklbmc = jqfklbmc;
        }

        public String getJqfklxmc() {
            return jqfklxmc;
        }

        public void setJqfklxmc(String jqfklxmc) {
            this.jqfklxmc = jqfklxmc;
        }

        public String getJqfkxlmc() {
            return jqfkxlmc;
        }

        public void setJqfkxlmc(String jqfkxlmc) {
            this.jqfkxlmc = jqfkxlmc;
        }

        public String getAjfssj() {
            return ajfssj;
        }

        public void setAjfssj(String ajfssj) {
            this.ajfssj = ajfssj;
        }

        public String getAjjssj() {
            return ajjssj;
        }

        public void setAjjssj(String ajjssj) {
            this.ajjssj = ajjssj;
        }

        public String getCjqk() {
            return cjqk;
        }

        public void setCjqk(String cjqk) {
            this.cjqk = cjqk;
        }

        public String getFsdd() {
            return fsdd;
        }

        public void setFsdd(String fsdd) {
            this.fsdd = fsdd;
        }

        public String getFksj() {
            return fksj;
        }

        public void setFksj(String fksj) {
            this.fksj = fksj;
        }

        public String getSsrs() {
            return ssrs;
        }

        public void setSsrs(String ssrs) {
            this.ssrs = ssrs;
        }

        public String getZhrs() {
            return zhrs;
        }

        public void setZhrs(String zhrs) {
            this.zhrs = zhrs;
        }

        public String getSwrs() {
            return swrs;
        }

        public void setSwrs(String swrs) {
            this.swrs = swrs;
        }
    }


}
