package cn.ffcs.dao;

import cn.ffcs.bean.FoodOrderRecords;
import cn.ffcs.bean.FoodOrderRecordsExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FoodOrderRecordsDao {
    /**
     * @param example
     */
    int countByExample(FoodOrderRecordsExample example);

    /**
     * 根据条件删除数据
     *
     * @param example
     */
    int deleteByExample(FoodOrderRecordsExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新添数据
     *
     * @param record
     */
    int insertSelective(FoodOrderRecords record);

    /**
     * 根据条件查询
     *
     * @param example
     */
    List <FoodOrderRecords> selectByExample(FoodOrderRecordsExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    FoodOrderRecords selectByPrimaryKey(Integer id);

    /**
     * 更新数据
     *
     * @param record
     */
    int updateByPrimaryKeySelective(FoodOrderRecords record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(FoodOrderRecords record);
}