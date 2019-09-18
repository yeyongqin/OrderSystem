package cn.ffcs.dao;

import cn.ffcs.bean.Food;
import cn.ffcs.bean.FoodExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FoodDao {
    /**
     * 根据条件删除食品
     *
     * @param example
     */
    int deleteByExample(FoodExample example);

    /**
     * 根据主键删除食品
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加食品
     *
     * @param record
     */
    int insertSelective(Food record);

    /**
     * 根据条件查询
     *
     * @param example
     */
    List <Food> selectByExample(FoodExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    Food selectByPrimaryKey(Integer id);

    /**
     * 更新食品
     *
     * @param record
     */
    int updateByPrimaryKeySelective(Food record);
}