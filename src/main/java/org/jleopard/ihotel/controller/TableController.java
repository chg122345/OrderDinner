/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-09-29  上午10:45
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.controller;

import org.jleopard.ihotel.core.annotation.Controller;
import org.jleopard.ihotel.core.annotation.Inject;
import org.jleopard.ihotel.core.annotation.RenderJson;
import org.jleopard.ihotel.core.annotation.RequestMapping;
import org.jleopard.ihotel.core.ienum.Method;
import org.jleopard.ihotel.entity.DinnerTable;
import org.jleopard.ihotel.service.DinnerTableService;

import java.util.List;

@Controller
public class TableController {

    @Inject
    private DinnerTableService service;

    @RequestMapping("/table")
    @RenderJson
    public List<DinnerTable> list(String name){
        return service.query();
    }

    @RequestMapping(value = "/table1",method = Method.POST)
    @RenderJson
    public DinnerTable table(DinnerTable t){
        System.out.println("获取到的参数-->" + t);
        return t;
    }
}
