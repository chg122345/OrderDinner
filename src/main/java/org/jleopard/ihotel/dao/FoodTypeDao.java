/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-09-28  下午3:43
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.dao;

import org.jleopard.exception.SqlSessionException;
import org.jleopard.ihotel.core.annotation.Bean;
import org.jleopard.ihotel.entity.FoodType;
import org.jleopard.session.SqlSession;

import java.util.List;

@Bean
public class FoodTypeDao extends BaseDao<FoodType> {

    public int deleteById(Integer id){
        SqlSession session = sessionFactory.openSession();
        try {
            int temp = session.delete(FoodType.class,id);
            session.commit();
            session.close();
            return temp;
        } catch (SqlSessionException e) {
            try {
                session.rollback();
            } catch (SqlSessionException e1) {
                e1.printStackTrace();
            }
        }
        return -1;
    }

    public List<FoodType> select(){
        SqlSession session = sessionFactory.openSession();
        try {
            List<FoodType> res = (List<FoodType>) session.get(FoodType.class);
            session.close();
            return res;
        } catch (SqlSessionException e) {
            e.printStackTrace();
        }
        return null;
    }
}