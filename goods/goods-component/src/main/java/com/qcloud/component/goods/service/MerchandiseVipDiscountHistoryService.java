package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.MerchandiseVipDiscountHistory;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountHistoryQuery;

public interface MerchandiseVipDiscountHistoryService {
	
	public boolean add(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory);	
	
	public MerchandiseVipDiscountHistory get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory);

	public Page<MerchandiseVipDiscountHistory> page(MerchandiseVipDiscountHistoryQuery query, int start, int count);
	
	public List<MerchandiseVipDiscountHistory> listAll();
}

