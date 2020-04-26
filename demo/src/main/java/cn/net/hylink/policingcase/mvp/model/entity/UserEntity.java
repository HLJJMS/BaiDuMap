package cn.net.hylink.policingcase.mvp.model.entity;

public class UserEntity  {

    /**
     * carno : 黑A66666
     * effective : 1
     * gxdwdm : 230000000008
     * gxdwmc : 测试机构
     * idcard : 110105198001010112
     * imei : tempHylinkCid
     * islogin : true
     * kqrbm : 00
     * kqrxm : 卡点测试
     * rltpdz : http://192.168.3.117:9000/img/7,2f2c5e9b40b278
     * sign_identification : qd
     * sign_time : 2020-03-31 09:10:15
     * user_id : 110105198001010112
     * xh : 1
     */

    private String carno;
    private String effective;
    private String gxdwdm;
    private String gxdwmc;
    private String idcard;
    private String imei;
    private boolean islogin;
    private String kqrbm;
    private String kqrxm;
    private String rltpdz;
    private String sign_identification;
    private String sign_time;
    private String user_id;
    private int xh;

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective;
    }

    public String getGxdwdm() {
        return gxdwdm;
    }

    public void setGxdwdm(String gxdwdm) {
        this.gxdwdm = gxdwdm;
    }

    public String getGxdwmc() {
        return gxdwmc;
    }

    public void setGxdwmc(String gxdwmc) {
        this.gxdwmc = gxdwmc;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public boolean isIslogin() {
        return islogin;
    }

    public void setIslogin(boolean islogin) {
        this.islogin = islogin;
    }

    public String getKqrbm() {
        return kqrbm;
    }

    public void setKqrbm(String kqrbm) {
        this.kqrbm = kqrbm;
    }

    public String getKqrxm() {
        return kqrxm;
    }

    public void setKqrxm(String kqrxm) {
        this.kqrxm = kqrxm;
    }

    public String getRltpdz() {
        return rltpdz;
    }

    public void setRltpdz(String rltpdz) {
        this.rltpdz = rltpdz;
    }

    public String getSign_identification() {
        return sign_identification;
    }

    public void setSign_identification(String sign_identification) {
        this.sign_identification = sign_identification;
    }

    public String getSign_time() {
        return sign_time;
    }

    public void setSign_time(String sign_time) {
        this.sign_time = sign_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getXh() {
        return xh;
    }

    public void setXh(int xh) {
        this.xh = xh;
    }
}
