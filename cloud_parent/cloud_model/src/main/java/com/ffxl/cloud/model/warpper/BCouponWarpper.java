package com.ffxl.cloud.model.warpper;


public class BCouponWarpper{
    
    private static final long serialVersionUID = 1L;
    /**
     * 优惠券id
     */
    private String couponId;
    
    /**
     * 活动id
     */
    private String saleId;
    /**
     * 优惠券名称
     */
    private String couponName;
    
    /**
     * 优惠券对应的范围code
     */
    private String category;
    
    /**
     * 优惠券对应的使用范围名称
     */
    private String categoryName;
    
    /**
     * 优惠券金额
     */
    private String couponAmount;
    
    /**
     * 优惠券有效期
     */
    private String validity;
    
    /**
     * 领取状态
     * 1:已领取
     * 2:可领取
     * 3:已抢光
     */
    private String receiveStatus;
    
    private String receiveStatusReasion;
    
    /**
     * 用户可领取次数
     */
    private int receiveTimes;
    
    /**
     * 已领取次数
     */
    private int alreadyReceiveTimes; 
    
    /**
     * 剩余领取次数
     */
    private int remainReceiveTimes;
    
    /**
     * 可领取人数
     */
    private int totalPersion; 
    
    /**
     * 已领取人数
     */
    private int alreadyTotalPersion;
    
    /**
     * 剩余领取人数
     */
    private int remainTotalPersion;
    
    
    public String getCouponId() {
        return couponId;
    }
    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }
    public String getSaleId() {
        return saleId;
    }
    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }
    public String getCouponName() {
        return couponName;
    }
    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
    
    
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCouponAmount() {
        return couponAmount;
    }
    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
    }
    public String getValidity() {
        return validity;
    }
    public void setValidity(String validity) {
        this.validity = validity;
    }
   
    
    public String getReceiveStatus() {
        return receiveStatus;
    }
    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }
    public int getReceiveTimes() {
        return receiveTimes;
    }
    public void setReceiveTimes(int receiveTimes) {
        this.receiveTimes = receiveTimes;
    }
    public int getAlreadyReceiveTimes() {
        return alreadyReceiveTimes;
    }
    public void setAlreadyReceiveTimes(int alreadyReceiveTimes) {
        this.alreadyReceiveTimes = alreadyReceiveTimes;
    }
    public int getRemainReceiveTimes() {
        return remainReceiveTimes;
    }
    public void setRemainReceiveTimes(int remainReceiveTimes) {
        this.remainReceiveTimes = remainReceiveTimes;
    }
    public int getTotalPersion() {
        return totalPersion;
    }
    public void setTotalPersion(int totalPersion) {
        this.totalPersion = totalPersion;
    }
    public int getAlreadyTotalPersion() {
        return alreadyTotalPersion;
    }
    public void setAlreadyTotalPersion(int alreadyTotalPersion) {
        this.alreadyTotalPersion = alreadyTotalPersion;
    }
    public int getRemainTotalPersion() {
        return remainTotalPersion;
    }
    public void setRemainTotalPersion(int remainTotalPersion) {
        this.remainTotalPersion = remainTotalPersion;
    }
    
    
    
    
}