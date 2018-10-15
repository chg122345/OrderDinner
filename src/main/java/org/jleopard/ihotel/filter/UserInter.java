/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-10-05  下午1:44
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.filter;

import org.jleopard.ihotel.entity.User;
import org.jleopard.mvc.inter.Interceptor;
import org.jleopard.mvc.inter.InterceptorRegistration;
import org.jleopard.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInter implements Interceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, InterceptorRegistration register) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && StringUtil.isNotEmpty(user.getId())){
          return true;
        }
        return false;
    }
}
