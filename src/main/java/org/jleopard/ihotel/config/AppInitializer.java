/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-09-26  上午9:49
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.config;

import org.jleopard.ihotel.filter.BrowserFilter;
import org.jleopard.ihotel.filter.EncodingFilter;
import org.jleopard.mvc.servlet.DispatcherServlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import javax.servlet.http.HttpServlet;
import java.util.Set;

@HandlesTypes({ HttpServlet.class, Filter.class })
public class AppInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("感兴趣的类型：");
        for (Class<?> claz : set) {
            System.err.println(claz.getName());
        }

        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcherServlet", DispatcherServlet.class);

        dynamic.addMapping("/");
        dynamic.addMapping("*.jsp");
        dynamic.setLoadOnStartup(1);
        // 字符过滤
        FilterRegistration.Dynamic charset = servletContext.addFilter("charset", EncodingFilter.class);

        charset.setInitParameter("encoding", "UTF-8");
        charset.setInitParameter("forceEncoding", "true");

        charset.addMappingForUrlPatterns(null, false, "/*");

        FilterRegistration.Dynamic browser = servletContext.addFilter("browser", BrowserFilter.class);
        browser.addMappingForUrlPatterns(null, false, "/*");
        //权限拦截器----------------暂时注释掉
       /* FilterRegistration.Dynamic authority = servletContext.addFilter("authority", SessionFilter.class);
        authority.addMappingForUrlPatterns(null, false, "/*");*/
    }
}
