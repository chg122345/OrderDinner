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
import org.jleopard.ihotel.core.annotation.Bean;
import org.jleopard.ihotel.entity.OrderDetail;
import org.jleopard.session.SqlSession;

import java.util.List;

@Bean
public class OrderDetialDao extends BaseDao<OrderDetail> {

    public List<OrderDetail> select(){
        SqlSession session = sessionFactory.openSession();
        try {
            List<OrderDetail> res = (List<OrderDetail>) session.get(OrderDetail.class);
            session.close();
            return res;
        } catch (SqlSessionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
