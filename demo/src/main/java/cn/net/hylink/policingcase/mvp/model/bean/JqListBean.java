package cn.net.hylink.policingcase.mvp.model.bean;

public class JqListBean {

    /**
     * currentPage : 1
     * showCount : 999
     * pd : {"carno":"","gxdwdm":"230000000000","mjdwmc":"黑龙江省","imei":"tempHylinkCid","isgx":1,"isnow":0,"jjdzt":"01"}
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

        /**
         * carno :
         * gxdwdm : 230000000000
         * mjdwmc : 黑龙江省
         * imei : tempHylinkCid
         * isgx : 1
         * isnow : 0
         * jjdzt : 01
         */

        private String carno;
        private String gxdwdm;
        private String mjdwmc;
        private String imei;
        private int isgx;
        private int isnow;
        private String jjdzt;

        public String getCarno() {
            return carno;
        }

        public void setCarno(String carno) {
            this.carno = carno;
        }

        public String getGxdwdm() {
            return gxdwdm;
        }

        public void setGxdwdm(String gxdwdm) {
            this.gxdwdm = gxdwdm;
        }

        public String getMjdwmc() {
            return mjdwmc;
        }

        public void setMjdwmc(String mjdwmc) {
            this.mjdwmc = mjdwmc;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public int getIsgx() {
            return isgx;
        }

        public void setIsgx(int isgx) {
            this.isgx = isgx;
        }

        public int getIsnow() {
            return isnow;
        }

        public void setIsnow(int isnow) {
            this.isnow = isnow;
        }

        public String getJjdzt() {
            return jjdzt;
        }

        public void setJjdzt(String jjdzt) {
            this.jjdzt = jjdzt;
        }
    }
}
