package com.oneapm.record;

public class MessageType {
    public final static int INFO_APPLY_SALE = 1;
    public final static int INFO_APPLY_SUPPORT = 2;
    public final static int INFO_APPLY_SALE_AGREE = 3;
    public final static int INFO_APPLY_SUPPORT_AGREE = 4;
    public final static int INFO_APPLY_SALE_REFUSE = 5;
    public final static int INFO_APPLY_SUPPORT_REFUSE = 6;
    
    /**
     * 提醒负责人
     */
    public final static int REMIND_HEAD_SALE = 7;
    public final static int REMIND_HEAD_SUPPORT = 8;
    /**
     * 分配销售负责人
     */
    public final static int ASSIGN_HEAD_SALE = 9;
    /**
     * 分配运营负责人
     */
    public final static int ASSIGN_HEAD_SUPPORT = 10;
    public final static int ASSIGN_HEAD_SALE_AGREE = 11;
    public final static int ASSIGN_HEAD_SUPPORT_AGREE = 12;
    public final static int ASSIGN_HEAD_SALE_REFUSE= 13;
    public final static int ASSIGN_HEAD_SUPPORT_REFUSE= 14;
    public final static int ADD_RECORD= 15;
    /**
     * 销售警告
     */
    public final static int SALE_WARMING= 16;
    /**
     * 销售被收回
     */
    public final static int SALE_BACK= 17;
}
