package cn.ffcs.service;

import java.util.Date;
import java.util.List;

import cn.ffcs.bean.FoodOrderRecordsExample;
import cn.ffcs.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ffcs.bean.FoodOrderRecords;
import cn.ffcs.dao.FoodOrderRecordsDao;
import cn.ffcs.util.OrderUtil;
import cn.ffcs.util.TimeUtil;

@Service
public class FoodOrderRecordsService {

    @Autowired
    private FoodOrderRecordsDao foodOrderRecordsDao;

    /**
     * 新增订单
     *
     * @param userId
     * @return
     */
    public int createNewRecords(int userId, int orderState) {
        String orderNum = OrderUtil.getOrderNumber();
        FoodOrderRecords record = new FoodOrderRecords();
        record.setUserId(userId);
        record.setRecordsNo(orderNum);
        record.setCreateTime(TimeUtil.convertCurrentTimeToDateType());
        record.setUpdateTime(TimeUtil.convertCurrentTimeToDateType());
        record.setOrderState(Constants.orderStateNumToCHN(orderState));
        foodOrderRecordsDao.insertSelective(record);
        return record.getId();
    }

    /**
     * 根据用户编号查询订单
     *
     * @param userId
     * @return
     */
    public List <FoodOrderRecords> selectByUserId(int userId) {
        FoodOrderRecordsExample example = new FoodOrderRecordsExample();
        FoodOrderRecordsExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return foodOrderRecordsDao.selectByExample(example);
    }

    /**
     * 更新总订单
     *
     * @param userId
     * @param orderState
     * @return
     */
    public int updateRecords(int userId, int id, int orderState) {
        FoodOrderRecords records = new FoodOrderRecords();
        records.setUserId(userId);
        records.setId(id);
        records.setOrderState(Constants.orderStateNumToCHN(orderState));
        return foodOrderRecordsDao.updateByPrimaryKeySelective(records);
    }
}
