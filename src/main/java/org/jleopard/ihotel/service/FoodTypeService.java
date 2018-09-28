package org.jleopard.ihotel.service;

import org.jleopard.ihotel.core.annotation.Bean;
import org.jleopard.ihotel.dao.FoodTypeDao;
import org.jleopard.ihotel.entity.FoodType;

import java.util.List;


@Bean
public class FoodTypeService {


    FoodTypeDao dao = new FoodTypeDao();

    public int add(FoodType foodType) {
        return dao.insert(foodType);

    }

    public int update(FoodType foodType) {
        return dao.update(foodType);

    }

    public int delete(int id) {
        return dao.deleteById(id);
    }

    public FoodType findById(Integer id) {
        FoodType var1 = new FoodType();
        var1.setId(id);
        List<FoodType> list = dao.select(var1);
        return list == null ? null : list.get(0);
    }

    public List<FoodType> query() {
        return dao.select();
    }

    public List<FoodType> query(FoodType foodType) {
        return dao.select(foodType);
    }

    public Integer getFirstType() {
        List<FoodType> list = query();
        return list == null ? null : list.get(0).getId();
    }


}
