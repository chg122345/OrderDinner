package org.jleopard.ihotel.service;

import org.jleopard.ihotel.dao.OrderDetialDao;
import org.jleopard.ihotel.entity.OrderDetail;
import org.jleopard.ihotel.entity.Orders;
import org.jleopard.mvc.core.annotation.Inject;
import org.jleopard.mvc.core.annotation.Service;

import java.util.List;


@Service
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
		return dao.select("orderid = ?",id);
	}

}
