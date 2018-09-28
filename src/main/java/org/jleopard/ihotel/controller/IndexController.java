/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-09-26  下午3:18
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.controller;

import org.jleopard.ihotel.core.annotation.Controller;
import org.jleopard.ihotel.core.annotation.RequestMapping;
import org.jleopard.ihotel.entity.DinnerTable;
import org.jleopard.ihotel.entity.FoodType;
import org.jleopard.ihotel.service.DinnerTableService;
import org.jleopard.ihotel.service.FoodService;
import org.jleopard.ihotel.service.FoodTypeService;
import org.jleopard.pageHelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    DinnerTableService tableService = new DinnerTableService();

    FoodTypeService foodTypeService =new FoodTypeService();

    FoodService foodService = new FoodService();

    @RequestMapping({"/","/index"})
    public String index(HttpServletRequest request, HttpServletResponse response){
            HttpSession session =request.getSession();
            Object obj =session.getAttribute("table_id");
            String table_id =request.getParameter("table_id");
            if(table_id !=null) {
                tableService.changeState(Integer.parseInt(table_id));
                if(obj ==null) {
                    session.setAttribute("table_id", table_id);
                }
            }
        List<DinnerTable> tables = tableService.query();
        request.setAttribute("table",tables);
        return "/app/index";
    }

    @RequestMapping("/caidan")
    public String menu(HttpServletRequest request, HttpServletResponse response){
        List<FoodType> foodtypes =foodTypeService.query();
        request.setAttribute("listFoodType", foodtypes);
        PageInfo all = foodService.getAll(1, 6);
        request.setAttribute("pb", all);
        return "/app/caidan";
    }

    @RequestMapping({"/add","del"})
    public String add(HttpServletRequest request, HttpServletResponse response){
        return "redirect:/index";
    }
}
