package cn.ffcs.dao;

import cn.ffcs.bean.UserAccount;

public interface UserAccountDao {

    /**
     * 新增账户
     *
     * @param record
     * @return
     */
    int insertSelective(UserAccount record);

    /**
     * 根据用户Id查询账户信息
     *
     * @param userId
     * @return
     */
    UserAccount selectByUserId(Integer userId);

    /**
     * 根据用户Id更新账户存款
     *
     * @param account
     * @return
     */
    int updateDepositByUserId(UserAccount account);
}
