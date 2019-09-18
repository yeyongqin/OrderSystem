package cn.ffcs.service;

import java.util.Date;
import java.util.List;

import cn.ffcs.bean.UserAccount;
import cn.ffcs.constant.Constants;
import cn.ffcs.dao.UserAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ffcs.bean.Food;
import cn.ffcs.bean.FoodOrderDetail;
import cn.ffcs.bean.FoodOrderDetailExample;
import cn.ffcs.dao.FoodOrderDetailDao;
import cn.ffcs.util.TimeUtil;

@Service
public class FoodOrderDetailService {

    @Autowired
    private FoodOrderDetailDao foodOrderDetailDao;

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private FoodOrderRecordsService foodOrderRecordsService;

    @Autowired
    private FoodService foodService;

    /**
     * 根据用户编号查询用户的购物车信息
     *
     * @param userId
     * @return
     */
    public List <FoodOrderDetail> queryFoodOrderDetailListByUserId(int userId) {
        FoodOrderDetailExample example = new FoodOrderDetailExample();
        FoodOrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andDataFlagEqualTo(Constants.DATA_FLAG_0);
        criteria.andUserIdEqualTo(userId);
        criteria.andOrderStateEqualTo(Constants.ORDER_SHOPPING_CART);
        // 菜品数量大于等于1
        criteria.andOrderCountGreaterThanOrEqualTo(1);
        criteria.andRecordsIdEqualTo(Constants.RECORDS_ID_0);
        return foodOrderDetailDao.selectByExample(example);
    }

    /**
     * 根据用户编号和订单编号查询订单详情
     *
     * @param userId
     * @return
     */
    public List <FoodOrderDetail> queryFoodOrderDetailListByRecordsNo(int userId, int recordsId) {
        FoodOrderDetailExample example = new FoodOrderDetailExample();
        FoodOrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andDataFlagEqualTo(Constants.DATA_FLAG_0);
        criteria.andUserIdEqualTo(userId);
        criteria.andOrderStateEqualTo(Constants.ORDER_PAYMENTED);
        criteria.andRecordsIdEqualTo(recordsId);
        return foodOrderDetailDao.selectByExample(example);
    }

    /**
     * 添加单件菜品进购物车
     *
     * @param foodId
     * @param userId
     * @return
     */
    public boolean doAddShoppingCar(int foodId, int userId) {
        //查询当前添加菜品的价格信息等
        Food food = foodService.queryFoodByFoodId(foodId);
        //先查询当前菜品是否已经存在于购物车
        FoodOrderDetailExample example = new FoodOrderDetailExample();
        FoodOrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andDataFlagEqualTo(Constants.DATA_FLAG_0);
        criteria.andFoodIdEqualTo(foodId);
        criteria.andUserIdEqualTo(userId);
        criteria.andOrderCountGreaterThanOrEqualTo(1);
        criteria.andOrderStateEqualTo(Constants.ORDER_SHOPPING_CART);
        criteria.andRecordsIdEqualTo(Constants.RECORDS_ID_0);
        List <FoodOrderDetail> FoodOrderDetailList = foodOrderDetailDao.selectByExample(example);
        if (FoodOrderDetailList.isEmpty() || FoodOrderDetailList.size() <= 0) {
            //不存在	直接新增
            FoodOrderDetail record = new FoodOrderDetail();
            record.setUserId(userId);
            record.setFoodId(foodId);
            record.setOrderCount(1);
            record.setSinglePrice(food.getPrice());
            record.setFoodName(food.getFoodName());
            record.setTotal(food.getPrice());
            return foodOrderDetailDao.insertSelective(record) > 0 ? true : false;

        } else {
            // 已经存在 更新
            // 需要更新的对象
            FoodOrderDetail FoodOrderDetail = FoodOrderDetailList.get(0);
            FoodOrderDetail record = new FoodOrderDetail();
            record.setId(FoodOrderDetail.getId());
            record.setOrderCount(FoodOrderDetail.getOrderCount() + 1);
            record.setTotal(FoodOrderDetail.getSinglePrice() + FoodOrderDetail.getTotal());
            return foodOrderDetailDao.updateByPrimaryKeySelective(record) > 0 ? true : false;
        }
    }

    /**
     * 操作购物车
     *
     * @param operateId
     * @param userId
     * @param operate
     * @return
     */
    public boolean operateCar(int operateId, int userId, int operate) {
        //查询当前操作的菜品
        FoodOrderDetail foodfoodOrderDetail = foodOrderDetailDao.selectByPrimaryKey(operateId);
        int num = foodfoodOrderDetail.getOrderCount();
        if (num <= 1 && operate == -1) {
            FoodOrderDetail record = new FoodOrderDetail();
            record.setId(operateId);
            record.setOrderCount(0);
            record.setDataFlag(Constants.DATA_FLAG_1);
            record.setTotal((float) 0);
            return foodOrderDetailDao.updateByPrimaryKeySelective(record) > 0 ? true : false;
        } else {
            FoodOrderDetail record = new FoodOrderDetail();
            record.setId(operateId);
            record.setOrderCount(num + operate);
            record.setTotal(foodfoodOrderDetail.getTotal() + foodfoodOrderDetail.getSinglePrice() * operate);
            return foodOrderDetailDao.updateByPrimaryKeySelective(record) > 0 ? true : false;
        }
    }

    /**
     * 支付
     *
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean doPay(int userId) {
        //先根据用户编号查询状态为购物车的订单
        FoodOrderDetailExample example = new FoodOrderDetailExample();
        FoodOrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andDataFlagEqualTo(Constants.DATA_FLAG_0);
        criteria.andOrderCountGreaterThanOrEqualTo(1);
        criteria.andUserIdEqualTo(userId);
        criteria.andOrderStateEqualTo(Constants.ORDER_SHOPPING_CART);
        criteria.andRecordsIdEqualTo(Constants.RECORDS_ID_0);
        List <FoodOrderDetail> existList = foodOrderDetailDao.selectByExample(example);
        if (existList.isEmpty() || existList.size() <= 0) {
            return false;
        } else {
            try {
                // 更新菜品销量
                Food newFood = new Food();
                for (FoodOrderDetail foodOrderDetail : existList) {
                    Integer foodId = foodOrderDetail.getFoodId();
                    Food oldFood = foodService.queryFoodByFoodId(foodId);
                    newFood.setId(foodId);
                    newFood.setSales(oldFood.getSales() + foodOrderDetail.getOrderCount());
                    foodService.updateFood(newFood);
                }
                //支付扣款
                float money = sumMoney(userId, Constants.RECORDS_ID_0, Constants.ORDER_SHOPPING_CART);
                UserAccount account = userAccountDao.selectByUserId(userId);
                float deposit = account.getDeposit();
                if (deposit >= money) {
                    account.setDeposit(deposit - money);
                    userAccountDao.updateDepositByUserId(account);
                } else {
                    return false;
                }

                //先生成总的订单
                int recordsId = foodOrderRecordsService.createNewRecords(userId, Constants.ORDER_PAYMENTED);
                //更新所有订单为支付成功 并把主表主键写入
                for (int i = 0; i < existList.size(); i++) {
                    FoodOrderDetail record = new FoodOrderDetail();
                    record.setRecordsId(recordsId);
                    record.setOrderState(Constants.ORDER_PAYMENTED);
                    record.setId(existList.get(i).getId());
                    record.setCreateTime(TimeUtil.convertCurrentTimeToDateType());
                    record.setUpdateTime(new Date());
                    int result = foodOrderDetailDao.updateByPrimaryKeySelective(record);
                    if (result <= 0) {
                        try {
                            throw new Exception("支付失败");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    /**
     * 计算总价
     *
     * @param userId
     * @return
     */
    public float sumMoney(int userId, int recordsId, int orderState) {
        FoodOrderDetailExample example = new FoodOrderDetailExample();
        FoodOrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andDataFlagEqualTo(Constants.DATA_FLAG_0);
        criteria.andUserIdEqualTo(userId);
        criteria.andOrderStateEqualTo(orderState);
        criteria.andOrderCountGreaterThanOrEqualTo(1);
        criteria.andRecordsIdEqualTo(recordsId);
        List <FoodOrderDetail> foodOrderDetailList = foodOrderDetailDao.selectByExample(example);
        float sum = 0;
        for (FoodOrderDetail foodOrderDetail : foodOrderDetailList) {
            sum += foodOrderDetail.getTotal();
        }
        return sum;
    }

}
