package cn.net.hylink.policingcase.mvp.model.bean;

public class CallBackLookBean {

    /**
     * currentPage : 1
     * showCount : 999
     * pd : {"jqbh":"53250020190723143521009","imei":"tempHylinkCid"}
     */

    private int currentPage;
    private int showCount;
    private PdBean pd;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getShowCount() {
        return showCount;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    public PdBean getPd() {
        return pd;
    }

    public void setPd(PdBean pd) {
        this.pd = pd;
    }

    public static class PdBean {
        public PdBean(String jqbh, String imei) {
            this.jqbh = jqbh;
            this.imei = imei;
        }

        /**
         * jqbh : 53250020190723143521009
         * imei : tempHylinkCid
         */

        private String jqbh;
        private String imei;

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
    }
}
