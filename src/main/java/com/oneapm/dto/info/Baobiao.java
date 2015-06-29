package com.oneapm.dto.info;

public class Baobiao {

    //注册（增量/总数）
    private int sign;
    private int sign_up;
    //登录（增量/总数）
    private int login;
    private int login_up;
    //非当天注册第一次登录（增量/总数）
    private int login_first;
    private int login_first_up;
    //下载（增量/总数）
    private int download;
    private int download_up;
    //第一次下载（增量/总数）
    private int download_first;
    private int download_first_up;
    //添加应用（增量/总数）
    private int app;
    private int app_up;
    //第一次添加应用（增量/总数）
    private int app_first;
    private int app_first_up;
    //有数据用户（增量/总数）
    private int data;
    private int data_up;
    //第一次有数据用户（增量/总数）
    private int data_first;
    private int data_first_up;
    //有数据应用（增量/总数）
    private int data_app;
    private int data_app_up;
    //第一次有数据应用（增量/总数）
    private int data_app_first;
    private int data_app_first_up;
    //企业数（增量/总数）
    private int value;
    private int value_up;
    //新标记企业（增量/总数）
    private int value_first;
    private int value_first_up;
    //开发者数（增量/总数）
    private int normal;
    private int normal_up;
    //新标记开发者（增量/总数）
    private int normal_first;
    private int normal_first_up;
    //未定义数（增量/总数）
    private int unknow;
    private int unknow_up;
    //新标记未定义（增量/总数）
    private int unknow_first;
    private int unknow_first_up;
    //关闭数（增量/总数）
    private int close;
    private int close_up;
    //新标记关闭（增量/总数）
    private int close_first;
    private int close_first_up;
    //销售重点数（增量/总数）
    private int saleimport;
    //销售普通数（增量/总数）
    private int salenormal;
    //添加记录（增量/总数）
    private int record;
    private int record_up;
    //分配到销售（增量/总数）
    private int tosale;
    private int tosale_up;
    //新分配到销售（增量/总数）
    private int tosale_first;
    private int tosale_first_up;
    //交流（增量/总数）
    private int tojiao;
    private int tojiao_up;
    //测试（增量/总数）
    private int toce;
    private int toce_up;
    //进入商务流程（增量/总数）
    private int tobu;
    private int tobu_up;
    //完成（增量/总数）
    private int finish;
    private int finish_up;
    //成交（增量/总数）
    private int success;
    private int success_up;
    //失败（增量/总数）
    private int fail;
    private int fail_up;
    
    private String dataTime;
    
    public Baobiao(int sign,int sign_up,int login,int login_up,int login_first,int login_first_up,
            int download, int download_up, int download_first, int download_first_up, int app,
            int app_up, int app_first, int app_first_up,int data,int data_up,int data_first,
            int data_first_up,int data_app,int data_app_up,int data_app_first,int data_app_first_up,
            int value,int value_up,int value_first,int value_first_up,int normal,int normal_up,
            int normal_first,int normal_first_up,int unknow,int unknow_up,int unknow_first,int unknow_first_up,
            int close,int close_up,int close_first,int close_first_up,int record,int record_up,int tosale,int tosale_up,
            int tosale_first,int tosale_first_up, int tojiao, int tojiao_up, int toce, int toce_up, int tobu,
            int tobu_up,int finish,int finish_up, int success,int success_up,int fail,int fail_up, int saleimport, int salenormal){
        
        setSign(sign);
        setSign_up(sign_up);
        setLogin(login);
        setLogin_up(login_up);
        setLogin_first(login_first);
        setLogin_first_up(login_first_up);
        setDownload(download);
        setDownload_up(download_up);
        setDownload_first(download_first);
        setDownload_first_up(download_first_up);
        setApp(data_app);
        setApp_up(app_up);
        setApp_first(app_first);
        setApp_first_up(app_first_up);
        setData(data);
        setData_up(data_up);
        setData_first(data_first);
        setData_first_up(data_first_up);
        setData_app(data_app);
        setData_app_up(data_app_up);
        setData_app_first(data_app_first);
        setData_app_first_up(data_app_first_up);
        setValue(value);
        setValue_up(value_up);
        setValue_first(value_first);
        setValue_first_up(value_first_up);
        setNormal(normal);
        setNormal_up(normal_up);
        setNormal_first(normal_first);
        setNormal_first_up(normal_first_up);
        setUnknow(unknow);
        setUnknow_up(unknow_up);
        setUnknow_first(unknow_first);
        setUnknow_first_up(unknow_first_up);
        setClose(close);
        setClose_up(close_up);
        setClose_first(close_first);
        setClose_first_up(close_first_up);
        setSaleimport(saleimport);
        setSalenormal(salenormal);
        setRecord(record);
        setRecord_up(record_up);
        setTosale(tosale);
        setTosale_up(tosale_up);
        setTosale_first(tosale_first);
        setTosale_first_up(tosale_first_up);
        setTojiao(tojiao);
        setTojiao_up(tojiao_up);
        setToce(toce);
        setToce_up(toce_up);
        setTobu(tobu);
        setTobu_up(tobu_up);
        setFinish(finish);
        setFinish_up(finish_up);
        setSuccess(success);
        setSuccess_up(success_up);
        setFail(fail);
        setFail_up(fail_up);
    }
    
    public int getSign() {
        return sign;
    }
    public void setSign(int sign) {
        this.sign = sign;
    }
    public int getSign_up() {
        return sign_up;
    }
    public void setSign_up(int sign_up) {
        this.sign_up = sign_up;
    }
    public int getLogin() {
        return login;
    }
    public void setLogin(int login) {
        this.login = login;
    }
    public int getLogin_up() {
        return login_up;
    }
    public void setLogin_up(int login_up) {
        this.login_up = login_up;
    }
    public int getLogin_first() {
        return login_first;
    }
    public void setLogin_first(int login_first) {
        this.login_first = login_first;
    }
    public int getLogin_first_up() {
        return login_first_up;
    }
    public void setLogin_first_up(int login_first_up) {
        this.login_first_up = login_first_up;
    }
    public int getDownload() {
        return download;
    }
    public void setDownload(int download) {
        this.download = download;
    }
    public int getDownload_up() {
        return download_up;
    }
    public void setDownload_up(int download_up) {
        this.download_up = download_up;
    }
    public int getDownload_first() {
        return download_first;
    }
    public void setDownload_first(int download_first) {
        this.download_first = download_first;
    }
    public int getDownload_first_up() {
        return download_first_up;
    }
    public void setDownload_first_up(int download_first_up) {
        this.download_first_up = download_first_up;
    }
    public int getApp() {
        return app;
    }
    public void setApp(int app) {
        this.app = app;
    }
    public int getApp_up() {
        return app_up;
    }
    public void setApp_up(int app_up) {
        this.app_up = app_up;
    }
    public int getApp_first() {
        return app_first;
    }
    public void setApp_first(int app_first) {
        this.app_first = app_first;
    }
    public int getApp_first_up() {
        return app_first_up;
    }
    public void setApp_first_up(int app_first_up) {
        this.app_first_up = app_first_up;
    }
    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }
    public int getData_up() {
        return data_up;
    }
    public void setData_up(int data_up) {
        this.data_up = data_up;
    }
    public int getData_first() {
        return data_first;
    }
    public void setData_first(int data_first) {
        this.data_first = data_first;
    }
    public int getData_first_up() {
        return data_first_up;
    }
    public void setData_first_up(int data_first_up) {
        this.data_first_up = data_first_up;
    }
    public int getData_app() {
        return data_app;
    }
    public void setData_app(int data_app) {
        this.data_app = data_app;
    }
    public int getData_app_up() {
        return data_app_up;
    }
    public void setData_app_up(int data_app_up) {
        this.data_app_up = data_app_up;
    }
    public int getData_app_first() {
        return data_app_first;
    }
    public void setData_app_first(int data_app_first) {
        this.data_app_first = data_app_first;
    }
    public int getData_app_first_up() {
        return data_app_first_up;
    }
    public void setData_app_first_up(int data_app_first_up) {
        this.data_app_first_up = data_app_first_up;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getValue_up() {
        return value_up;
    }
    public void setValue_up(int value_up) {
        this.value_up = value_up;
    }
    public int getValue_first() {
        return value_first;
    }
    public void setValue_first(int value_first) {
        this.value_first = value_first;
    }
    public int getValue_first_up() {
        return value_first_up;
    }
    public void setValue_first_up(int value_first_up) {
        this.value_first_up = value_first_up;
    }
    public int getNormal() {
        return normal;
    }
    public void setNormal(int normal) {
        this.normal = normal;
    }
    public int getNormal_up() {
        return normal_up;
    }
    public void setNormal_up(int normal_up) {
        this.normal_up = normal_up;
    }
    public int getNormal_first() {
        return normal_first;
    }
    public void setNormal_first(int normal_first) {
        this.normal_first = normal_first;
    }
    public int getNormal_first_up() {
        return normal_first_up;
    }
    public void setNormal_first_up(int normal_first_up) {
        this.normal_first_up = normal_first_up;
    }
    public int getUnknow() {
        return unknow;
    }
    public void setUnknow(int unknow) {
        this.unknow = unknow;
    }
    public int getUnknow_up() {
        return unknow_up;
    }
    public void setUnknow_up(int unknow_up) {
        this.unknow_up = unknow_up;
    }
    public int getUnknow_first() {
        return unknow_first;
    }
    public void setUnknow_first(int unknow_first) {
        this.unknow_first = unknow_first;
    }
    public int getUnknow_first_up() {
        return unknow_first_up;
    }
    public void setUnknow_first_up(int unknow_first_up) {
        this.unknow_first_up = unknow_first_up;
    }
    public int getClose() {
        return close;
    }
    public void setClose(int close) {
        this.close = close;
    }
    public int getClose_up() {
        return close_up;
    }
    public void setClose_up(int close_up) {
        this.close_up = close_up;
    }
    public int getClose_first() {
        return close_first;
    }
    public void setClose_first(int close_first) {
        this.close_first = close_first;
    }
    public int getClose_first_up() {
        return close_first_up;
    }
    public void setClose_first_up(int close_first_up) {
        this.close_first_up = close_first_up;
    }
//    public int getSaleimport() {
//        return saleimport;
//    }
//    public void setSaleimport(int saleimport) {
//        this.saleimport = saleimport;
//    }
//    public int getSaleimport_up() {
//        return saleimport_up;
//    }
//    public void setSaleimport_up(int saleimport_up) {
//        this.saleimport_up = saleimport_up;
//    }
//    public int getSaleimport_first() {
//        return saleimport_first;
//    }
//    public void setSaleimport_first(int saleimport_first) {
//        this.saleimport_first = saleimport_first;
//    }
//    public int getSaleimport_first_up() {
//        return saleimport_first_up;
//    }
//    public void setSaleimport_first_up(int saleimport_first_up) {
//        this.saleimport_first_up = saleimport_first_up;
//    }
//    public int getSalenormal() {
//        return salenormal;
//    }
//    public void setSalenormal(int salenormal) {
//        this.salenormal = salenormal;
//    }
//    public int getSalenormal_up() {
//        return salenormal_up;
//    }
//    public void setSalenormal_up(int salenormal_up) {
//        this.salenormal_up = salenormal_up;
//    }
//    public int getSalenormal_first() {
//        return salenormal_first;
//    }
//    public void setSalenormal_first(int salenormal_first) {
//        this.salenormal_first = salenormal_first;
//    }
//    public int getSalenormal_first_up() {
//        return salenormal_first_up;
//    }
//    public void setSalenormal_first_up(int salenormal_first_up) {
//        this.salenormal_first_up = salenormal_first_up;
//    }
    public int getRecord() {
        return record;
    }
    public void setRecord(int record) {
        this.record = record;
    }
    public int getRecord_up() {
        return record_up;
    }
    public void setRecord_up(int record_up) {
        this.record_up = record_up;
    }
    public int getTosale() {
        return tosale;
    }
    public void setTosale(int tosale) {
        this.tosale = tosale;
    }
    public int getTosale_up() {
        return tosale_up;
    }
    public void setTosale_up(int tosale_up) {
        this.tosale_up = tosale_up;
    }
    public int getTosale_first_up() {
        return tosale_first_up;
    }
    public void setTosale_first_up(int tosale_first_up) {
        this.tosale_first_up = tosale_first_up;
    }
    public int getTosale_first() {
        return tosale_first;
    }
    public void setTosale_first(int tosale_first) {
        this.tosale_first = tosale_first;
    }
    public int getTobu() {
        return tobu;
    }
    public void setTobu(int tobu) {
        this.tobu = tobu;
    }
    public int getTobu_up() {
        return tobu_up;
    }
    public void setTobu_up(int tobu_up) {
        this.tobu_up = tobu_up;
    }
    public int getFinish() {
        return finish;
    }
    public void setFinish(int finish) {
        this.finish = finish;
    }
    public int getFinish_up() {
        return finish_up;
    }
    public void setFinish_up(int finish_up) {
        this.finish_up = finish_up;
    }
    public int getSuccess() {
        return success;
    }
    public void setSuccess(int success) {
        this.success = success;
    }
    public int getSuccess_up() {
        return success_up;
    }
    public void setSuccess_up(int success_up) {
        this.success_up = success_up;
    }
    public int getFail() {
        return fail;
    }
    public void setFail(int fail) {
        this.fail = fail;
    }
    public int getFail_up() {
        return fail_up;
    }
    public void setFail_up(int fail_up) {
        this.fail_up = fail_up;
    }

    public int getTojiao() {
        return tojiao;
    }

    public void setTojiao(int tojiao) {
        this.tojiao = tojiao;
    }

    public int getToce_up() {
        return toce_up;
    }

    public void setToce_up(int toce_up) {
        this.toce_up = toce_up;
    }

    public int getTojiao_up() {
        return tojiao_up;
    }

    public void setTojiao_up(int tojiao_up) {
        this.tojiao_up = tojiao_up;
    }

    public int getToce() {
        return toce;
    }

    public void setToce(int toce) {
        this.toce = toce;
    }

    public int getSaleimport() {
        return saleimport;
    }

    public void setSaleimport(int saleimport) {
        this.saleimport = saleimport;
    }

    public int getSalenormal() {
        return salenormal;
    }

    public void setSalenormal(int salenormal) {
        this.salenormal = salenormal;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }
}
