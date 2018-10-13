/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-10-13  下午9:22
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.controller;

import org.jleopard.ihotel.entity.FoodType;
import org.jleopard.ihotel.service.FoodService;
import org.jleopard.ihotel.service.FoodTypeService;
import org.jleopard.mvc.core.annotation.*;
import org.jleopard.pageHelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String PREFIX_PATH = "/sys/";

    @Inject
    private FoodTypeService foodTypeService;

    @Inject
    private FoodService foodService;

    @RequestMapping({"/","/index"})
    public String index(){
        return PREFIX_PATH+"index";
    }

    @RequestMapping("/foodtypeList")
    public String foodtypeList(HttpServletRequest request){
        List<FoodType> foodTypes = foodTypeService.query();
        request.setAttribute("listFoodType",foodTypes);
        return PREFIX_PATH+"/type/foodtype_list";
    }

    @RequestMapping("/boardList")
    public String boardList(HttpServletRequest request){
        return PREFIX_PATH+"/boardList";
    }

    @RequestMapping("/foodList")
    public String foodList(HttpServletRequest request){
        PageInfo pageInfo = foodService.getAll(1, 6, null, null);
        request.setAttribute("foodList",pageInfo);
        return PREFIX_PATH+"/foodList";
    }

    @RequestMapping("/delFood")
    @RenderJson
    public String delFood(@RequestParam("id") Integer id){
        return "删除成功";
    }

    @RequestMapping("/orderList")
    public String orderList(HttpServletRequest request){
        return PREFIX_PATH+"/orderList";
    }
}
