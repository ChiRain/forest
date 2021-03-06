package com.qcloud.component.orderform.model;

import java.util.Date;
import java.math.BigDecimal;

public class ExchangeOrder {
	
	//ID
	private long id;		
	
	//订单号
	private String orderNumber;		
	
	//总单ID
	private long orderId;		
	
	//下单时间
	private Date orderDate;		
	
	//子单ID
	private long subOrderId;		
	
	//商家ID
	private long merchantId;		
	
	//门店ID
	private long storeId;		
	
	//状态(1:已申请 2:通过 3:未通过 )
	private int state;		
	
	//申请换货时间
	private Date time;		
	
	//用户id
	private long userId;		
	
	//说明
	private String explain;		
	
	//原因
	private String reason;		
	
	//换货单号
	private String exchangeNumber;		
	
	//物流公司
	private String userLogisticsCompany;		
	
	//物流查询号
	private String userLogisticsNumber;		
	
	//物流公司
	private String merchantLogisticsCompany;		
	
	//物流查询号
	private String merchantLogisticsNumber;		
	
	private Date lastUpdateTime;		
	
	private Date stateValidTime;		

	public ExchangeOrder(){
	
	}

	public ExchangeOrder(long id,String orderNumber,long orderId,Date orderDate,long subOrderId,long merchantId,long storeId,int state,Date time,long userId,String explain,String reason,String exchangeNumber,String userLogisticsCompany,String userLogisticsNumber,String merchantLogisticsCompany,String merchantLogisticsNumber,Date lastUpdateTime,Date stateValidTime){
		this.id = id;		
		this.orderNumber = orderNumber;		
		this.orderId = orderId;		
		this.orderDate = orderDate;		
		this.subOrderId = subOrderId;		
		this.merchantId = merchantId;		
		this.storeId = storeId;		
		this.state = state;		
		this.time = time;		
		this.userId = userId;		
		this.explain = explain;		
		this.reason = reason;		
		this.exchangeNumber = exchangeNumber;		
		this.userLogisticsCompany = userLogisticsCompany;		
		this.userLogisticsNumber = userLogisticsNumber;		
		this.merchantLogisticsCompany = merchantLogisticsCompany;		
		this.merchantLogisticsNumber = merchantLogisticsNumber;		
		this.lastUpdateTime = lastUpdateTime;		
		this.stateValidTime = stateValidTime;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}	
		
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}	
		
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}	
		
	public void setSubOrderId(long subOrderId) {
		this.subOrderId = subOrderId;
	}

	public long getSubOrderId() {
		return subOrderId;
	}	
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}	
		
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}	
		
	public void setExchangeNumber(String exchangeNumber) {
		this.exchangeNumber = exchangeNumber;
	}

	public String getExchangeNumber() {
		return exchangeNumber;
	}	
		
	public void setUserLogisticsCompany(String userLogisticsCompany) {
		this.userLogisticsCompany = userLogisticsCompany;
	}

	public String getUserLogisticsCompany() {
		return userLogisticsCompany;
	}	
		
	public void setUserLogisticsNumber(String userLogisticsNumber) {
		this.userLogisticsNumber = userLogisticsNumber;
	}

	public String getUserLogisticsNumber() {
		return userLogisticsNumber;
	}	
		
	public void setMerchantLogisticsCompany(String merchantLogisticsCompany) {
		this.merchantLogisticsCompany = merchantLogisticsCompany;
	}

	public String getMerchantLogisticsCompany() {
		return merchantLogisticsCompany;
	}	
		
	public void setMerchantLogisticsNumber(String merchantLogisticsNumber) {
		this.merchantLogisticsNumber = merchantLogisticsNumber;
	}

	public String getMerchantLogisticsNumber() {
		return merchantLogisticsNumber;
	}	
		
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}	
		
	public void setStateValidTime(Date stateValidTime) {
		this.stateValidTime = stateValidTime;
	}

	public Date getStateValidTime() {
		return stateValidTime;
	}	
		
}
