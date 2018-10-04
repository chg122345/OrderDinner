package org.jleopard.ihotel.service;


import org.jleopard.ihotel.dao.FoodDao;
import org.jleopard.ihotel.entity.Food;
import org.jleopard.ihotel.entity.FoodType;
import org.jleopard.mvc.core.annotation.Inject;
import org.jleopard.mvc.core.annotation.Service;
import org.jleopard.pageHelper.PageInfo;

import java.util.List;

@Service
public class FoodService {

    @Inject
    private FoodDao dao;

    public int delete(Integer id) {
        return dao.deleteById(id);

    }

    public int update(Food food) {
        return dao.update(food);
    }

    public List<Food> query() {
        return dao.select();
    }

    public List<Food> query(Food food) {
        return dao.select(food);
    }

    public List<Food> findByType(Integer type) {

        Food var1 = new Food();
        var1.setFoodType_id(new FoodType().initId(type));
        return dao.select(var1);
    }

    public int add(Food food) {
        return dao.insert(food);

    }

    public Food findById(Integer id) {
        Food var1 = new Food();
        var1.setId(id);
        List<Food> list = query(var1);
        return list == null ? null : list.get(0);
    }

    public PageInfo getAll(int page, int pageSize) {
        return dao.selectToPage(page, pageSize);
    }


}
