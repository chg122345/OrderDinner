/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-09-26  上午9:39
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.config;

/**
 * 项目基础配置
 * 数据源等
 */
public interface DemoConfigConstant {

    String BASE_PACKAGE = "org.jleopard.ihotel";

    String CONTROLLER_PACKAGE = "org.jleopard.ihotel.controller";

    String ENTITY_PACKAGE = "org.jleopard.ihotel.entity";

    String DATASOURCE_URL = "jdbc:mysql://127.0.0.1:3306/ihotel?characterEncoding=UTF-8";

    String DATASOURCE_USER = "root";

    String DATASOURCE_PASSWORD = "chg122345";

    String DATASOURCE_DRIVER = "com.mysql.jdbc.Driver";
}
