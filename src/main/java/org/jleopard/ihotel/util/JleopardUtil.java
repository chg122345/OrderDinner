/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-09-28  下午2:43
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.util;

import org.jleopard.jdbc.BaseDataSource;
import org.jleopard.session.Configuration;
import org.jleopard.session.sessionFactory.SqlSessionFactory;

import javax.sql.DataSource;

import static org.jleopard.ihotel.config.DemoConfigConstant.*;

public class JleopardUtil {

    public static SqlSessionFactory getSessionFactory(){
        DataSource dataSource= new BaseDataSource(DATASOURCE_URL, DATASOURCE_USER, DATASOURCE_PASSWORD, DATASOURCE_DRIVER);
        Configuration configuration = new Configuration(ENTITY_PACKAGE,  dataSource);
        configuration.setAutoCommit(false);
        SqlSessionFactory sessionFactory = SqlSessionFactory.Builder.build(configuration);
        return sessionFactory;
    }
}
