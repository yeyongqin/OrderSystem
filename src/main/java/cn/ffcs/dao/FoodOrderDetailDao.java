package cn.ffcs.dao;

import cn.ffcs.bean.FoodOrderDetail;
import cn.ffcs.bean.FoodOrderDetailExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FoodOrderDetailDao {

    /**
     * 根据条件删除
     *
     * @param example
     */
    int deleteByExample(FoodOrderDetailExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @param record
     */
    int insertSelective(FoodOrderDetail record);

    /**
     * 根据条件查询
     *
     * @param example
     */
    List <FoodOrderDetail> selectByExample(FoodOrderDetailExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    FoodOrderDetail selectByPrimaryKey(Integer id);

    /**
     * 更新数据
     *
     * @param record
     */
    int updateByPrimaryKeySelective(FoodOrderDetail record);
}