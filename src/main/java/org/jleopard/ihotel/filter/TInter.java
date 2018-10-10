/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-10-05  下午1:44
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.filter;

import org.jleopard.mvc.inter.Interceptor;
import org.jleopard.mvc.inter.InterceptorRegistration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TInter implements Interceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, InterceptorRegistration register) throws Exception {
        return false;
    }
}
