package cn.ffcs.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ffcs.bean.FoodType;
import cn.ffcs.bean.FoodTypeExample;
import cn.ffcs.dao.FoodTypeDao;

@Service
public class FoodTypeService {

    @Autowired
    private FoodTypeDao foodTypeDao;

    /**
     * 查询所有菜品类别
     *
     * @return
     */
    public List <FoodType> queryAllFoodType() {
        FoodTypeExample example = new FoodTypeExample();
        FoodTypeExample.Criteria criteria = example.createCriteria();
        criteria.andDataFlagEqualTo(0);
        return foodTypeDao.selectByExample(example);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public FoodType queryFoodTypeById(int id) {
        return foodTypeDao.selectByPrimaryKey(id);
    }

    /**
     * 添加类别
     *
     * @param typeName
     * @return
     */
    public boolean addNewFoodType(String typeName) {
        FoodType record = new FoodType();
        record.setCreateTime(new Date());
        record.setTypeName(typeName);
        return foodTypeDao.insertSelective(record) > 0 ? true : false;
    }
}
