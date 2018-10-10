/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-09-29  上午10:45
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.controller;

import org.jleopard.ihotel.filter.TInter;
import org.jleopard.mvc.core.annotation.*;
import org.jleopard.mvc.core.ienum.Method;
import org.jleopard.ihotel.entity.DinnerTable;
import org.jleopard.ihotel.service.DinnerTableService;
import org.jleopard.mvc.inter.Before;
import org.jleopard.mvc.inter.Clear;

import java.util.List;

@Controller
@Before(TInter.class)
public class TableController {

    @Inject
    private DinnerTableService service;

    @RequestMapping("/table")
    @RenderJson
    @Clear(TInter.class)
    public List<DinnerTable> list(@RequestParam("name") String name, @RequestParam("pass") String pass){
        System.out.println(name+pass);
        return service.query();
    }

    @RequestMapping(value = "/tt",method = Method.GET)
    @RenderJson
    @Clear(TInter.class)
    public Integer list1(@RequestParam("name") String name, @RequestParam("pass") Integer pass){
        System.out.println(name+pass);
        return pass;
    }

    @RequestMapping(value = "/table1",method = Method.POST)
    @RenderJson
    public DinnerTable table(DinnerTable t){
        System.out.println("获取到的参数-->" + t);
        service.add(t);
        return t;
    }
}
