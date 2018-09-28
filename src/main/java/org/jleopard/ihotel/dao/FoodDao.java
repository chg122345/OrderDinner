/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-09-28  下午3:02
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.dao;

import org.jleopard.exception.SqlSessionException;
import org.jleopard.ihotel.core.annotation.Bean;
import org.jleopard.ihotel.entity.Food;
import org.jleopard.ihotel.entity.FoodType;
import org.jleopard.pageHelper.PageInfo;
import org.jleopard.session.SqlSession;

import java.util.List;

@Bean
public class FoodDao extends BaseDao<Food> {

    public int deleteById(Integer id){
        SqlSession session = sessionFactory.openSession();
        try {
            int temp = session.delete(Food.class,id);
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

    public List<Food> select(){
        SqlSession session = sessionFactory.openSession();
        try {
            List<Food> res = (List<Food>) session.get(Food.class);
            session.close();
            return res;
        } catch (SqlSessionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PageInfo selectToPage(int page,int pageSize){
        SqlSession session = sessionFactory.openSession();
        try {
            PageInfo res = session.getJoinToPage(Food.class,new Class[]{FoodType.class},page,pageSize,"where 1 = 1","");
            session.close();
            return res;
        } catch (SqlSessionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
