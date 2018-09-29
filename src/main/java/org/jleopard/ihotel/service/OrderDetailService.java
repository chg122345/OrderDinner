package org.jleopard.ihotel.service;

import org.jleopard.ihotel.core.annotation.Bean;
import org.jleopard.ihotel.core.annotation.Inject;
import org.jleopard.ihotel.dao.OrderDetialDao;
import org.jleopard.ihotel.entity.OrderDetail;

import java.util.List;


@Bean
public class OrderDetailService {

	@Inject
	private OrderDetialDao dao;

	public int add(OrderDetail od) {
		return dao.insert(od);

	}

	public List<OrderDetail> query() {
		return dao.select();
	}

	public List<OrderDetail> findByOrderid(Integer id) {
		OrderDetail var1 = new OrderDetail();
		var1.setId(id);
		return dao.select(var1);
	}

}
