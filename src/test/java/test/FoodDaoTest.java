/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-09-28  下午3:07
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package test;

import org.jleopard.ihotel.dao.FoodDao;
import org.jleopard.ihotel.entity.Food;
import org.jleopard.ihotel.entity.FoodType;

public class FoodDaoTest {

    public static void main(String[] args) {
        FoodDao dao = new FoodDao();
        FoodType foodType = new FoodType();
        foodType.setId(2);
        Food food = new Food(3,"HHHFF",5.6,5.5,"","",foodType);
        System.out.println(dao.insert(food));
       /* FoodTypeDao dao1 = new FoodTypeDao();
        FoodType foodType = new FoodType();
        foodType.setTypeName("推荐");
        System.out.println(dao1.insert(foodType));*/
    }
}
