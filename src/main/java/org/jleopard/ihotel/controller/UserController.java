/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-10-15  上午9:37
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.controller;

import org.jleopard.ihotel.entity.User;
import org.jleopard.ihotel.service.UserService;
import org.jleopard.mvc.core.annotation.*;
import org.jleopard.mvc.core.ienum.Method;
import org.jleopard.util.StringUtil;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Inject
    private UserService userService;

    @RequestMapping(value = "/login",method = Method.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login",method = Method.POST)
    @RenderJson
    public Integer login(HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password){
        User user = userService.login(email, password);
        if (user != null && StringUtil.isNotEmpty(user.getId())){
            session.setAttribute("user",user);
           return 200;
        }
        return 400;
    }


    @RequestMapping(value = "/register",method = Method.POST)
    @RenderJson
    public Map<String,Object> register(User user){
        Map<String,Object> map = new HashMap<>();
        int temp = userService.save(user);
        if (temp > 0){
            map.put("code",200);
            map.put("msg","注册成功");
        }else {
            map.put("code",400);
            map.put("msg","注册失败");
        }
        return map;
    }
}
