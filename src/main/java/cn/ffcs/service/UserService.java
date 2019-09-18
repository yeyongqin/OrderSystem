package cn.ffcs.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.ffcs.bean.FoodOrderRecordsExample;
import cn.ffcs.bean.UserAccount;
import cn.ffcs.dao.FoodOrderRecordsDao;
import cn.ffcs.dao.UserAccountDao;
import cn.ffcs.util.Md5Utils;
import cn.ffcs.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ffcs.bean.User;
import cn.ffcs.bean.UserExample;
import cn.ffcs.dao.UserDao;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private FoodOrderRecordsDao foodOrderRecordsDao;

    public User queryUserByPhoneAndPassword(String phone, String userPassword) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        criteria.andUserPasswordEqualTo(Md5Utils.md5(userPassword));
        criteria.andDataFlagEqualTo(0);
        List <User> userList = userDao.selectByExample(example);
        User user = null;
        if (!userList.isEmpty()) {
            user = userList.get(0);
        }
        return user;
    }

    public int addNewUser(String phone, String userPassword, String name) {
        // 对密码进行MD5加密
        userPassword = Md5Utils.md5(userPassword);
        // 获取当前时间
        Date createTime = new Date();
        User user = new User();
        user.setPhone(phone);
        user.setUserPassword(userPassword);
        user.setName(name);
        user.setCreateTime(createTime);
        userDao.insertSelective(user);
        return user.getId();
    }

    public int addNewUserAccount(int userId) {
        UserAccount userAccount = new UserAccount();
        userAccount.setDeposit((float) 0);
        userAccount.setUserId(userId);
        userAccountDao.insertSelective(userAccount);
        return userAccount.getId();
    }

    public UserAccount queryUserAccount(int userId) {
        return userAccountDao.selectByUserId(userId);
    }

    public User queryUserByPhone(String phone) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        criteria.andDataFlagEqualTo(0);
        List <User> userList = userDao.selectByExample(example);
        User user = null;
        if (!userList.isEmpty()) {
            user = userList.get(0);
        }
        return user;
    }

    public User queryUserById(int id) {
        User user = userDao.selectByPrimaryKey(id);
        return user;
    }

    public int deleteUserByUserId(int id) {
        return userDao.deleteByPrimaryKey(id);
    }

    public int selectOrderNumByState(int userId, String orderState) {
        FoodOrderRecordsExample example = new FoodOrderRecordsExample();
        FoodOrderRecordsExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andOrderStateEqualTo(orderState);
        return foodOrderRecordsDao.countByExample(example);
    }
}
