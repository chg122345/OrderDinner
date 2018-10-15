/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-10-15  上午9:30
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.service;

import org.jleopard.ihotel.dao.UserDao;
import org.jleopard.ihotel.entity.User;
import org.jleopard.mvc.core.annotation.Inject;
import org.jleopard.mvc.core.annotation.Service;
import org.jleopard.util.CollectionUtil;

import java.util.List;

@Service
public class UserService {

    @Inject
    private UserDao userDao;

    /**
     * 验证邮箱是否存在
     * 存在 --> true
     *
     * @param email
     * @return
     */
    public boolean checkEmail(String email) {
        User user = new User();
        user.setEmail(email);
        List<User> select = userDao.select(user);
        return CollectionUtil.isNotEmpty(select);
    }

    public User login(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        List<User> select = userDao.select(user);
        return CollectionUtil.isNotEmpty(select) ? select.get(0) : null;
    }

    public int save(User user){
        return userDao.insert(user);
    }
}
