package org.jleopard.ihotel.service;

import org.jleopard.ihotel.dao.OrdersDao;
import org.jleopard.ihotel.entity.Orders;
import org.jleopard.mvc.core.annotation.Inject;
import org.jleopard.mvc.core.annotation.Service;
import org.jleopard.pageHelper.PageInfo;

import java.util.List;

@Service
public class OrdersService {

	@Inject
    private OrdersDao dao;

	public int update(Orders orders) {
		return dao.update(orders);

	}

	public List<Orders> query() {
		return dao.select(new Orders());
	}

	public int add(Orders orders) {
		return dao.insert(orders);

	}

	public int getCount() {
		return getAll(1,1).getTotalRows();
	}

	public PageInfo getAll(int page,int pageSize) {
		return dao.selectToPage(page,pageSize);
	}

	public List<Orders> query(Orders orders) {
		return dao.select(orders);
	}
}
