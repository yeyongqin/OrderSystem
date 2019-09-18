package cn.ffcs.dao;

import cn.ffcs.bean.User;
import cn.ffcs.bean.UserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserDao {

    /**
     * 根据条件删除用户
     *
     * @param example
     * @return
     */
    int deleteByExample(UserExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增用户
     *
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 根据条件查询
     *
     * @param example
     * @return
     */
    List <User> selectByExample(UserExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * @param record
     */
    int updateByPrimaryKeySelective(User record);
}