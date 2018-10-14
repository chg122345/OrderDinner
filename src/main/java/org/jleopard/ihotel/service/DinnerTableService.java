package org.jleopard.ihotel.service;

import org.jleopard.ihotel.dao.DinnerTableDao;
import org.jleopard.ihotel.entity.DinnerTable;
import org.jleopard.mvc.core.annotation.Inject;
import org.jleopard.mvc.core.annotation.Service;

import java.util.Date;
import java.util.List;


@Service
public class DinnerTableService {


   @Inject
    private DinnerTableDao dao ;

    public int add(DinnerTable dt) {
       return dao.insert(dt);

    }

    public int delete(Integer id) {
        return dao.deleteById(id);

    }

    public int update(DinnerTable dt) {
        return dao.update(dt);

    }

    public List<DinnerTable> query(DinnerTable dt) {
        return dao.select(dt);
    }

    public List<DinnerTable> query() {
        return dao.select();
    }

    public DinnerTable changeState(Integer id) {
        DinnerTable var1 = new DinnerTable();
        var1.setId(id);
        DinnerTable table = dao.select(var1) == null ? null : dao.select(var1).get(0);
        Byte status = table.getTableStatus();
        if (status == 0) {
            status = 1;
            Date date = new Date();
            table.setOrderDate(date);
        } else if (status == 1) {
            status = 0;
            table.setOrderDate(null);
        }
        table.setTableStatus(status);
        dao.update(table);
        return table;
    }


    public DinnerTable findById(Integer id) {
        DinnerTable var1 = new DinnerTable();
        var1.setId(id);
        DinnerTable table = dao.select(var1) == null ? null : dao.select(var1).get(0);
        return table;
    }

    public int quitTable(Integer id) {
        DinnerTable var1 = new DinnerTable();
        var1.setId(id);
        var1.setTableStatus(Byte.valueOf("0"));
        var1.setOrderDate(null);
        int temp = dao.update(var1);
        return temp;
    }


}
