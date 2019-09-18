package cn.ffcs.service;

import java.util.List;

import cn.ffcs.bean.Food;
import cn.ffcs.bean.FoodExample;
import cn.ffcs.constant.Constants;
import cn.ffcs.dao.FoodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    @Autowired
    private FoodDao foodDao;

    /**
     * 食客最爱
     *
     * @return
     */
    public List <Food> queryFavouriteFoodList() {
        FoodExample example = new FoodExample();
        FoodExample.Criteria criteria = example.createCriteria();
        criteria.andDataFlagEqualTo(Constants.DATA_FLAG_0);
        example.setOrderByClause("sales desc,id Desc");
        return foodDao.selectByExample(example);
    }

    /**
     * recommen设置为1的对象  每日推荐
     *
     * @return
     */
    public List <Food> queryRecommendFoodList() {
        FoodExample example = new FoodExample();
        FoodExample.Criteria criteria = example.createCriteria();
        criteria.andRecommendEqualTo(1);
        criteria.andDataFlagEqualTo(0);
        example.setOrderByClause("sales Desc");
        return foodDao.selectByExample(example);
    }

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    public Food queryFoodByFoodId(int id) {
        return foodDao.selectByPrimaryKey(id);
    }

    /**
     * 添加菜品
     *
     * @param food
     * @return
     */
    public int addNewFood(Food food) {
        return foodDao.insertSelective(food) > 0 ? food.getId() : 0;
    }

    /**
     * 更新菜品
     *
     * @param food
     * @return
     */
    public boolean updateFood(Food food) {
        return foodDao.updateByPrimaryKeySelective(food) > 0 ? true : false;
    }

    /**
     * 查询所有菜品
     *
     * @return
     */
    public List <Food> queryAllFood() {
        FoodExample exmaple = new FoodExample();
        exmaple.setOrderByClause("id desc");
        FoodExample.Criteria criteria = exmaple.createCriteria();
        criteria.andDataFlagEqualTo(0);
        return foodDao.selectByExample(exmaple);
    }

}
