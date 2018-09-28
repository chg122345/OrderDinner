/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-09-28  下午3:31
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.dao;

import org.jleopard.exception.SqlSessionException;
import org.jleopard.ihotel.util.JleopardUtil;
import org.jleopard.session.SqlSession;
import org.jleopard.session.sessionFactory.SqlSessionFactory;

import java.io.Serializable;
import java.util.List;

public class BaseDao<T> {

    protected SqlSessionFactory sessionFactory = JleopardUtil.getSessionFactory();

    public int insert (T t){
        SqlSession session = sessionFactory.openSession();
        try {
            int temp = session.save(t);
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

    public int update(T t){
        SqlSession session = sessionFactory.openSession();
        try {
            int temp = session.update(t);
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

    public int updateByWhere(T t, String where, Serializable... args){
        SqlSession session = sessionFactory.openSession();
        try {
            int temp = session.updateByWhere(t,where,args);
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

    public int delete(T t){
        SqlSession session = sessionFactory.openSession();
        try {
            int temp = session.delete(t);
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

    public List<T> select(T t){
        SqlSession session = sessionFactory.openSession();
        try {
            List<T> res = (List<T>) session.get(t);
            session.close();
            return res;
        } catch (SqlSessionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
