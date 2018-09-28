/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-09-28  下午8:55
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package test;

import org.jleopard.ihotel.entity.Food;
import org.jleopard.ihotel.entity.FoodType;
import org.jleopard.ihotel.service.FoodService;
import org.jleopard.ihotel.util.BeanUtil;

public class ServiceTest {

    public static void main(String[] args) {
        FoodService dao = BeanUtil.getInstance(FoodService.class);
        FoodType foodType = new FoodType();
        foodType.setId(2);
        Food food = new Food(3,"HHHFF",5.6,5.5,"","",foodType);
        System.out.println(dao.getAll(2,3).getList());
       /* FoodTypeDao dao1 = new FoodTypeDao();
        FoodType foodType = new FoodType();
        foodType.setTypeName("推荐");
        System.out.println(dao1.insert(foodType));*/
    }
}
