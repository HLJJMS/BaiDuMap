package cn.net.hylink.policingcase.mvp.model.bean;

public class QwkbBean {
    String idCard;
    String month;

    public QwkbBean(String idCard, String month) {
        this.idCard = idCard;
        this.month = month;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
