package cn.ffcs.dao;

import cn.ffcs.bean.FoodType;
import cn.ffcs.bean.FoodTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FoodTypeDao {

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加数据
     *
     * @param record
     * @return
     */
    int insertSelective(FoodType record);

    /**
     * 根据条件查询
     *
     * @param example
     * @return
     */
    List <FoodType> selectByExample(FoodTypeExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     * @return
     */
    FoodType selectByPrimaryKey(Integer id);

    /**
     * 更新数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(FoodType record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(FoodType record);
}