package com.qcloud.component.goods.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseVipDiscount {
	
	//ID
	private long id;		
	
	//用户ID
	private long userId;		
	
	//单一商品ID
	private long merchandiseItemId;		
	
	//vip价格
	private double price;		
	
	//大客户折扣
	private double discount;		
	
	//结算方式：  1按价格 2按折扣
	private int paymentType;		
	
	private long classifyId;		
	
	private String classifyBSID;		

	public MerchandiseVipDiscount(){
	
	}

	public MerchandiseVipDiscount(long id,long userId,long merchandiseItemId,double price,double discount,int paymentType,long classifyId,String classifyBSID){
		this.id = id;		
		this.userId = userId;		
		this.merchandiseItemId = merchandiseItemId;		
		this.price = price;		
		this.discount = discount;		
		this.paymentType = paymentType;		
		this.classifyId = classifyId;		
		this.classifyBSID = classifyBSID;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setMerchandiseItemId(long merchandiseItemId) {
		this.merchandiseItemId = merchandiseItemId;
	}

	public long getMerchandiseItemId() {
		return merchandiseItemId;
	}	
		
	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}	
		
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDiscount() {
		return discount;
	}	
		
	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public int getPaymentType() {
		return paymentType;
	}	
		
	public void setClassifyId(long classifyId) {
		this.classifyId = classifyId;
	}

	public long getClassifyId() {
		return classifyId;
	}	
		
	public void setClassifyBSID(String classifyBSID) {
		this.classifyBSID = classifyBSID;
	}

	public String getClassifyBSID() {
		return classifyBSID;
	}	
		
}
