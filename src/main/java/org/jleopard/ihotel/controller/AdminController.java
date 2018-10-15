/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-10-13  下午9:22
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.controller;

import org.jleopard.ihotel.entity.*;
import org.jleopard.ihotel.filter.UserInter;
import org.jleopard.ihotel.service.*;
import org.jleopard.mvc.core.annotation.*;
import org.jleopard.mvc.core.ienum.Method;
import org.jleopard.mvc.inter.Before;
import org.jleopard.mvc.upload.MultipartFile;
import org.jleopard.pageHelper.PageInfo;
import org.jleopard.util.FileUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Before(UserInter.class)
@RequestMapping("/admin")
public class AdminController {

    private static final String PREFIX_PATH = "/sys/";

    @Inject
    private FoodTypeService foodTypeService;

    @Inject
    private FoodService foodService;

    @Inject
    private DinnerTableService tableService;

    @Inject
    private OrdersService ordersService;

    @Inject
    private OrderDetailService detailService;

    @RequestMapping({"", "/index"})
    public String index() {
        return PREFIX_PATH + "index";
    }

    /**
     *  菜系
     * @param request
     * @return
     */
    @RequestMapping(value = "/foodtypeList", method = Method.GET)
    public String foodtypeList(HttpServletRequest request) {
        List<FoodType> foodTypes = foodTypeService.query();
        request.setAttribute("listFoodType", foodTypes);
        return PREFIX_PATH + "/type/foodtype_list";
    }

    @RequestMapping(value = "/foodType", method = Method.GET)
    public String foodType() {
        return PREFIX_PATH + "/type/foodtype_save";
    }

    @RequestMapping(value = "/foodType", method = Method.POST)
    @RenderJson
    public String foodType(@RequestParam("typeName") String typeName) {
        FoodType foodType = new FoodType();
        foodType.setTypeName(typeName);
        int temp = foodTypeService.add(foodType);
        if (temp > 0) {
            return "添加成功";
        }
        return "添加失败";
    }

    @RequestMapping("/delFoodType")
    @RenderJson
    public String delFoodType(@RequestParam("id") Integer id) {
        int temp = foodTypeService.delete(id);
        if (temp > 0) {
            return "删除成功";
        }
        return "删除失败";
    }

    @RequestMapping(value = "/updateFoodType",method = Method.GET)
    public String updateFoodType(HttpServletRequest request, @RequestParam("id") Integer id){
        FoodType foodType = foodTypeService.findById(id);
        request.setAttribute("foodType",foodType);
        return PREFIX_PATH +"type/foodtype_update";
    }

    @RequestMapping(value = "/updateFoodType",method = Method.POST)
    @RenderJson
    public String updateFoodType(FoodType foodType){
        int temp = foodTypeService.update(foodType);
        if (temp >0){
            return "更新成功";
        }
        return "更新失败";
    }

    /**
     * 餐桌
     * @param request
     * @return
     */
    @RequestMapping("/boardList")
    public String boardList(HttpServletRequest request) {
        List<DinnerTable> tables = tableService.query();
        request.setAttribute("tables",tables);
        return PREFIX_PATH + "/boardList";
    }

    @RequestMapping("/delTable")
    @RenderJson
    public String delTable(@RequestParam("id") Integer id){
        int temp = tableService.delete(id);
        if (temp > 0){
            return "删除成功";
        }
        return "删除失败";
    }

    @RequestMapping("/changeTable")
    @RenderJson
    public String changeTable(@RequestParam("id") Integer id){
        DinnerTable table = new DinnerTable();
        table.setId(id);
        table.setTableStatus(Byte.valueOf("0"));
        int temp = tableService.update(table);
        if (temp > 0){
            return "成功";
        }
        return "失败";
    }

    @RequestMapping(value = "/addTable",method = Method.GET)
    public String addTable(){
        return PREFIX_PATH + "saveBoard";
    }

    @RequestMapping(value = "/addTable",method = Method.POST)
    @RenderJson
    public String addTable(DinnerTable dinnerTable){
        dinnerTable.setTableStatus(Byte.valueOf("0"));
        int temp = tableService.add(dinnerTable);
        if (temp > 0){
            return "成功";
        }
        return "失败";
    }

    /**
     *  菜单
     * @param request
     * @return
     */
    @RequestMapping("/foodList")
    public String foodList(HttpServletRequest request) {
        PageInfo pageInfo = foodService.getAll(1, 10, null, null);
        request.setAttribute("foodList", pageInfo);
        return PREFIX_PATH + "/foodList";
    }

    @RequestMapping(value = "/updateFood", method = Method.GET)
    public String updateFood(HttpServletRequest request, @RequestParam("id") Integer id) {
        Food food = foodService.findById(id);
        request.setAttribute("food", food);
        return PREFIX_PATH + "updateFood";
    }

    @RequestMapping(value = "/updateFood", method = Method.POST)
    @RenderJson
    public String updateFood(HttpServletRequest request, Food food, MultipartFile file) {
        String path = request.getSession().getServletContext().getRealPath("/");
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String realPath = path + fileName;
            File filePath = FileUtil.createFile(realPath);
            try {
                file.transferTo(filePath);
                food.setImg(realPath);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        int temp = foodService.update(food);
        if (temp > 0) {
            request.setAttribute("food", food);
            return "更新成功";
        }
        return "更新失败";
    }

    @RequestMapping(value = "/saveFood", method = Method.GET)
    public String saveFood(HttpServletRequest request) {
        List<FoodType> foodTypes = foodTypeService.query();
        request.setAttribute("foodTypes", foodTypes);
        return PREFIX_PATH + "saveFood";
    }

    @RequestMapping(value = "/saveFood", method = Method.POST)
    @RenderJson
    public String saveFood(HttpServletRequest request, @RequestParam("foodType") Integer foodTypeId, Food food, MultipartFile file) {
        System.err.println(" --->" + foodTypeId);
        food.setFoodType_id(new FoodType().initId(foodTypeId));
        String path = request.getSession().getServletContext().getRealPath("/");
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String realPath = path + fileName;
            File filePath = FileUtil.createFile(realPath);
            try {
                file.transferTo(filePath);
                food.setImg(realPath);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        int temp = foodService.add(food);
        if (temp > 0) {
            return "添加成功";
        }
        return "添加失败";
    }

    @RequestMapping("/delFood")
    @RenderJson
    public String delFood(@RequestParam("id") Integer id) {
        int temp = foodService.delete(id);
        if (temp > 0) {
            return "删除成功";
        }
        return "删除失败";
    }

    @RequestMapping("/orderList")
    public String orderList(HttpServletRequest request) {
        PageInfo pageInfo = ordersService.getAll(1, 10, null, null);
        request.setAttribute("orders",pageInfo);
        return PREFIX_PATH + "/orderList";
    }

    @RequestMapping("/changeOrder")
    @RenderJson
    public String changeOrder(@RequestParam("id") Integer id){
        Orders orders = new Orders();
        orders.setId(id);
        orders.setOrderStatus(Byte.valueOf("1"));
        int temp = ordersService.update(orders);
        if (temp > 0){
            return "成功";
        }
        return "失败";
    }

    @RequestMapping("/orderDetail")
    public String orderDetail(HttpServletRequest request, @RequestParam("id") Integer id){
        List<OrderDetail> orderDetails = detailService.findByOrderid(id);
        List<OrderDetail> orderDetails$ = new ArrayList<>();
        for (OrderDetail detail:orderDetails){
            Food food = foodService.findById(detail.getFood().getId());
            detail.setFood(food);
            orderDetails$.add(detail);
        }
        request.setAttribute("orderDetails",orderDetails$);
        return PREFIX_PATH +"orderDetail";
    }
}
