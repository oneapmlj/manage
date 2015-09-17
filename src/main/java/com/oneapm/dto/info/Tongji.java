package com.oneapm.dto.info;

public class Tongji {
    
    private Long id;
    //总数
    private long total;
    private int totalUp;
    //用户进度
//    private long goutong;
//    private int goutongUp;
    
    private long jiaoliu;
    private int jiaoliuUp;
    
    private long ceshi;
    private int ceshiUp;
    
//    private long shengchan;
//    private int shengchanUp;
    
    private long caigou;
    private int caigouUp;
    
    private long wancheng;
    private int wanchengUp;
    
    private long wancheng_success;
    private int wancheng_successUp;
    
    private long wancheng_fail;
    private int wancheng_failUp;
    
    //用户定义
    private long point;
    private int pointUp;
    
    private long common;
    private int commonUp;
    
    private long unbin;
    private int unbinUp;
    
    private long unuse;
    private int unuseUp;
    
    //注册数
    private long sign;
    private int signUp;
    //登录数
    private long login;
    private int loginUp;
    //下载数
    private long download;
    private int downloadUp;
    //添加应用数
    private long app;
    private int appUp;
    //有数据数
    private long data;
    private int dataUp;
    //有数据应用数
    private long appData;
    private int appDataUp;
    //活跃状态
    private int huoyue;
    private int huoyueUP;
    
    private int zhongjian;
    private int zhongjianUp;
    
    private int xiumian;
    private int xiumianUP;
    private int geli;
    private int geliUP;
    //数据时间
    private String data_time;
    
    private long group;
    private int groupUp;
    
    public Tongji(){}
    public Tongji(long group, Long id, long total,long sign,long login,long download,long app,long data, long appData){
        setGroup(group);
        setTotal(total);
        setSign(sign);
        setLogin(login);
        setDownload(download);
        setApp(app);
        setData(data);
        setId(id);
        setAppData(appData);
    }
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public long getCeshi() {
        return ceshi;
    }
    public void setCeshi(long ceshi) {
        this.ceshi = ceshi;
    }
    public long getCaigou() {
        return caigou;
    }
    public void setCaigou(long caigou) {
        this.caigou = caigou;
    }
    public long getWancheng() {
        return wancheng;
    }
    public void setWancheng(long wancheng) {
        this.wancheng = wancheng;
    }
    public long getPoint() {
        return point;
    }
    public void setPoint(long point) {
        this.point = point;
    }
    public long getCommon() {
        return common;
    }
    public void setCommon(long common) {
        this.common = common;
    }
    public long getUnbin() {
        return unbin;
    }
    public void setUnbin(long unbin) {
        this.unbin = unbin;
    }
    public long getUnuse() {
        return unuse;
    }
    public void setUnuse(long unuse) {
        this.unuse = unuse;
    }
    public long getSign() {
        return sign;
    }
    public void setSign(long sign) {
        this.sign = sign;
    }
    public long getLogin() {
        return login;
    }
    public void setLogin(long login) {
        this.login = login;
    }
    public long getDownload() {
        return download;
    }
    public void setDownload(long download) {
        this.download = download;
    }
    public long getApp() {
        return app;
    }
    public void setApp(long app) {
        this.app = app;
    }
    public long getData() {
        return data;
    }
    public void setData(long data) {
        this.data = data;
    }
    public long getJiaoliu() {
        return jiaoliu;
    }
    public void setJiaoliu(long jiaoliu) {
        this.jiaoliu = jiaoliu;
    }
    public String getData_time() {
        return data_time;
    }
    public void setData_time(String data_time) {
        this.data_time = data_time;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getTotalUp() {
        return totalUp;
    }
    public void setTotalUp(int totalUp) {
        this.totalUp = totalUp;
    }
    public int getJiaoliuUp() {
        return jiaoliuUp;
    }
    public void setJiaoliuUp(int jiaoliuUp) {
        this.jiaoliuUp = jiaoliuUp;
    }
    public int getCeshiUp() {
        return ceshiUp;
    }
    public void setCeshiUp(int ceshiUp) {
        this.ceshiUp = ceshiUp;
    }
    public int getCaigouUp() {
        return caigouUp;
    }
    public void setCaigouUp(int caigouUp) {
        this.caigouUp = caigouUp;
    }
    public int getWanchengUp() {
        return wanchengUp;
    }
    public void setWanchengUp(int wanchengUp) {
        this.wanchengUp = wanchengUp;
    }
    public int getPointUp() {
        return pointUp;
    }
    public void setPointUp(int pointUp) {
        this.pointUp = pointUp;
    }
    public int getCommonUp() {
        return commonUp;
    }
    public void setCommonUp(int commonUp) {
        this.commonUp = commonUp;
    }
    public int getUnbinUp() {
        return unbinUp;
    }
    public void setUnbinUp(int unbinUp) {
        this.unbinUp = unbinUp;
    }
    public int getUnuseUp() {
        return unuseUp;
    }
    public void setUnuseUp(int unuseUp) {
        this.unuseUp = unuseUp;
    }
    public int getSignUp() {
        return signUp;
    }
    public void setSignUp(int signUp) {
        this.signUp = signUp;
    }
    public int getLoginUp() {
        return loginUp;
    }
    public void setLoginUp(int loginUp) {
        this.loginUp = loginUp;
    }
    public int getDownloadUp() {
        return downloadUp;
    }
    public void setDownloadUp(int downloadUp) {
        this.downloadUp = downloadUp;
    }
    public int getAppUp() {
        return appUp;
    }
    public void setAppUp(int appUp) {
        this.appUp = appUp;
    }
    public int getDataUp() {
        return dataUp;
    }
    public void setDataUp(int dataUp) {
        this.dataUp = dataUp;
    }
    public long getAppData() {
        return appData;
    }
    public void setAppData(long appData) {
        this.appData = appData;
    }
    public int getAppDataUp() {
        return appDataUp;
    }
    public void setAppDataUp(int appDataUp) {
        this.appDataUp = appDataUp;
    }
    public long getWancheng_success() {
        return wancheng_success;
    }
    public void setWancheng_success(long wancheng_success) {
        this.wancheng_success = wancheng_success;
    }
    public int getWancheng_successUp() {
        return wancheng_successUp;
    }
    public void setWancheng_successUp(int wancheng_successUp) {
        this.wancheng_successUp = wancheng_successUp;
    }
    public long getWancheng_fail() {
        return wancheng_fail;
    }
    public void setWancheng_fail(long wancheng_fail) {
        this.wancheng_fail = wancheng_fail;
    }
    public int getWancheng_failUp() {
        return wancheng_failUp;
    }
    public void setWancheng_failUp(int wancheng_failUp) {
        this.wancheng_failUp = wancheng_failUp;
    }
public int getHuoyue() {
        return huoyue;
}
public void setHuoyue(int huoyue) {
        this.huoyue = huoyue;
}
public int getHuoyueUP() {
        return huoyueUP;
}
public void setHuoyueUP(int huoyueUP) {
        this.huoyueUP = huoyueUP;
}
public int getZhongjian() {
        return zhongjian;
}
public void setZhongjian(int zhongjian) {
        this.zhongjian = zhongjian;
}
public int getZhongjianUp() {
        return zhongjianUp;
}
public void setZhongjianUp(int zhongjianUp) {
        this.zhongjianUp = zhongjianUp;
}
public int getXiumian() {
        return xiumian;
}
public void setXiumian(int xiumian) {
        this.xiumian = xiumian;
}
public int getXiumianUP() {
        return xiumianUP;
}
public void setXiumianUP(int xiumianUP) {
        this.xiumianUP = xiumianUP;
}
public int getGeli() {
        return geli;
}
public void setGeli(int geli) {
        this.geli = geli;
}
public long getGroup() {
        return group;
}
public void setGroup(long group) {
        this.group = group;
}
public int getGroupUp() {
        return groupUp;
}
public void setGroupUp(int groupUp) {
        this.groupUp = groupUp;
}
    

}
