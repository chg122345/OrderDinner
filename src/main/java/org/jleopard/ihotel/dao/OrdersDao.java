/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-09-28  下午5:21
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.dao;

import org.jleopard.exception.SqlSessionException;
import org.jleopard.ihotel.entity.DinnerTable;
import org.jleopard.ihotel.entity.Orders;
import org.jleopard.mvc.core.annotation.Component;
import org.jleopard.mvc.core.annotation.Inject;
import org.jleopard.pageHelper.PageInfo;
import org.jleopard.session.SqlSession;
import org.jleopard.session.sessionFactory.SqlSessionFactory;

import java.io.Serializable;

@Component
public class OrdersDao extends BaseDao<Orders> {

    @Inject
    private SqlSessionFactory sessionFactory;

    public PageInfo selectToPage(int page, int pageSize, String where, Serializable args){
        SqlSession session = sessionFactory.openSession();
        try {
            PageInfo res = session.getJoinToPage(Orders.class,new Class[]{DinnerTable.class},page,pageSize,where,args);
            session.close();
            return res;
        } catch (SqlSessionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
