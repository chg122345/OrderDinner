/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-09-26  下午3:18
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.controller;

import org.jleopard.ihotel.entity.*;
import org.jleopard.ihotel.filter.TInter;
import org.jleopard.ihotel.service.*;
import org.jleopard.mvc.core.annotation.*;
import org.jleopard.mvc.core.ienum.Method;
import org.jleopard.mvc.inter.Before;
import org.jleopard.mvc.upload.MultipartFile;
import org.jleopard.pageHelper.PageInfo;
import org.jleopard.util.CollectionUtil;
import org.jleopard.util.FileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Inject
    private DinnerTableService tableService;

    @Inject
    private FoodTypeService foodTypeService;

    @Inject
    private FoodService foodService;

    @Inject
    private OrdersService ordersService;

    @Inject
    private OrderDetailService orderDetailService;

    private static final String PREFIX_PATH = "/app/";

    @RequestMapping({"/","/index"})
    public String index(HttpServletRequest request){
        List<DinnerTable> tables = tableService.query();
        request.setAttribute("table",tables);
        return PREFIX_PATH+"index";
    }

    @RequestMapping("/caidan")
    public String caidan(HttpServletRequest request,@RequestParam("id") Integer tableId){
        if (tableId != null){
            request.getSession().setAttribute("tableId",tableId);
        }
        List<FoodType> foodtypes =foodTypeService.query();
        request.setAttribute("listFoodType", foodtypes);
        PageInfo all = foodService.getAll(1, 6);
        request.setAttribute("pb", all);
        return PREFIX_PATH+"caidan";
    }

    @RequestMapping("/caixiangxi")
    public String caixiangxi(HttpServletRequest request,@RequestParam("id") Integer id){
        Food food = foodService.findById(id);
        request.setAttribute("food",food);
        return PREFIX_PATH + "caixiangxi";
    }

    @RequestMapping("/clientCart")
    @RenderJson
    public String clientCart(HttpSession session,@RequestParam("id") Integer id){
        Food food = foodService.findById(id);
        Cart cart = new Cart(food.getId(),food,1);
        List<Cart> carts = new ArrayList<>();
        carts.add(cart);
        List<Cart> carts1 = (List<Cart>) session.getAttribute("carts");
        if (CollectionUtil.isEmpty(carts1)){
            session.setAttribute("carts",carts);
        }else {
            if (carts1.contains(cart)){
                int i = carts1.indexOf(cart);
                Cart cart1 = carts1.get(i);
                cart1.setNumber(cart1.getNumber()+1);
                carts1.set(i,cart1);
            }else{
                carts1.add(cart);
            }
            session.setAttribute("carts",carts1);
        }
        return "添加成功！";
    }

    @RequestMapping("/look")
    public String look(){
        return PREFIX_PATH + "clientCart";
    }

    @RequestMapping("/delete")
    @RenderJson
    public String delete(HttpSession session,@RequestParam("id") Integer id){
        Cart cart = new Cart();
        cart.setTargetId(id);
        List<Cart> carts1 = (List<Cart>) session.getAttribute("carts");
        if (CollectionUtil.isEmpty(carts1)){
            return "您的订单为空";
        }else {
            carts1.remove(cart);
            session.setAttribute("carts",carts1);
        }
        return "删除成功";
    }

    @RequestMapping("/order")
    public String order(HttpSession session){
        List<Cart> carts = (List<Cart>) session.getAttribute("carts");
        if (CollectionUtil.isEmpty(carts)){
            return PREFIX_PATH + "clientOrderList";
        }
        Orders orders = new Orders();
        Double allTotal = 0.0;
        List<OrderDetail> orderDetails = new ArrayList();
        for (Cart cart :carts){
            allTotal += cart.getTotal();
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setFood(cart.getFood());
            orderDetail.setFoodCount(cart.getNumber());
            orderDetails.add(orderDetail);
        }
        orders.setTotalPrice(allTotal);
        Integer tableId = (Integer) session.getAttribute("tableId");
        orders.setTable(new DinnerTable(tableId));
        orders.setOrderStatus(Byte.valueOf("1"));
        try {
            int temp = ordersService.add(orders);
            if (temp > 0){
                List<Orders> query = ordersService.query(orders);
                Orders orders1 = CollectionUtil.isNotEmpty(query)?query.get(0):null;
                orderDetails.stream().forEach(i->{
                    i.setOrderid(orders1);
                    orderDetailService.add(i);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        orders.setOrderDetailList(orderDetails);
        session.removeAttribute("carts");
        session.setAttribute("order",orders);
        return PREFIX_PATH + "clientOrderList";
    }

    @RequestMapping(value = "/upload",method = Method.POST)
    @RenderJson
    public String upload1(HttpServletRequest request, @RequestParam("name") String name, @RequestParam("size") String size, DinnerTable t, MultipartFile[] files){
        System.out.println("name-->" + name +"size-->" + size +"TTTT-->" +t);
        String path= request.getSession().getServletContext().getRealPath("/");
        for (MultipartFile file:files){
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String realPath = path + fileName;
                File filePath = FileUtil.createFile(realPath);
                try {
                    file.transferTo(filePath);
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
        return "success";
    }
    @RequestMapping(value = "/upload",method = Method.ALL)
    @Before(TInter.class)
    public String upload(){
        return "upload";
    }
}
