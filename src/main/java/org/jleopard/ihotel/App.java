/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-10-04  下午9:44
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel;

import org.jleopard.mvc.core.ApplicationInitializer;
import org.jleopard.mvc.core.annotation.Component;
import org.jleopard.mvc.view.View;
import org.jleopard.mvc.view.jsp.JSPViewResolver;

import static org.jleopard.ihotel.config.DemoConfigConstant.BASE_PACKAGE;

@Component
public class App implements ApplicationInitializer {

    @Override
    public String getBasePackage() {
        return BASE_PACKAGE;
    }

    @Override
    public View getViewResolver() {
        return new JSPViewResolver("",".jsp");
    }
}
