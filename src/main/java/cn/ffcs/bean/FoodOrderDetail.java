package cn.ffcs.bean;

import java.util.Date;

public class FoodOrderDetail {
    /**
     * 主键
     * 表字段 : foodorderdetail.id
     */
    private Integer id;

    /**
     * 主表的主键
     * 表字段 : foodorderdetail.recordsId
     */
    private Integer recordsId;

    /**
     * 用户编号
     * 表字段 : foodorderdetail.userId
     */
    private Integer userId;

    /**
     * 菜品编号
     * 表字段 : foodorderdetail.foodId
     */
    private Integer foodId;

    /**
     * 菜品名称
     * 表字段 : foodorderdetail.foodName
     */
    private String foodName;

    /**
     * 数量
     * 表字段 : foodorderdetail.orderCount
     */
    private Integer orderCount;

    /**
     * 菜品状态 0 购物车 1待支付 2待后台支付 3后台确认支付 4制作中 5已派送 6已评价 7 退款中 8 已退款
     * 表字段 : foodorderdetail.orderState
     */
    private Integer orderState;

    /**
     * 备注
     * 表字段 : foodorderdetail.remark
     */
    private String remark;

    /**
     * 创建时间
     * 表字段 : foodorderdetail.createTime
     */
    private String createTime;

    /**
     * 失效时长
     * 表字段 : foodorderdetail.closeSpaceTime
     */
    private Integer closeSpaceTime;

    /**
     * 0有效 1无效
     * 表字段 : foodorderdetail.dataFlag
     */
    private Integer dataFlag;

    /**
     * 单件价格
     * 表字段 : foodorderdetail.singlePrice
     */
    private Float singlePrice;

    /**
     * 单品价格
     * 表字段 : foodorderdetail.total
     */
    private Float total;

    /**
     * 更新时间
     * 表字段 : foodorderdetail.updateTime
     */
    private Date updateTime;

    /**
     * 获取：主键
     * 表字段：foodorderdetail.id
     *
     * @return foodorderdetail.id：主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：主键
     * 表字段：foodorderdetail.id
     *
     * @param id
     *            foodorderdetail.id：主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主表的主键
     * 表字段：foodorderdetail.recordsId
     *
     * @return foodorderdetail.recordsId：主表的主键
     */
    public Integer getRecordsId() {
        return recordsId;
    }

    /**
     * 设置：主表的主键
     * 表字段：foodorderdetail.recordsId
     *
     * @param recordsId
     *            foodorderdetail.recordsId：主表的主键
     */
    public void setRecordsId(Integer recordsId) {
        this.recordsId = recordsId;
    }

    /**
     * 获取：用户编号
     * 表字段：foodorderdetail.userId
     *
     * @return foodorderdetail.userId：用户编号
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置：用户编号
     * 表字段：foodorderdetail.userId
     *
     * @param userId
     *            foodorderdetail.userId：用户编号
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：菜品编号
     * 表字段：foodorderdetail.foodId
     *
     * @return foodorderdetail.foodId：菜品编号
     */
    public Integer getFoodId() {
        return foodId;
    }

    /**
     * 设置：菜品编号
     * 表字段：foodorderdetail.foodId
     *
     * @param foodId
     *            foodorderdetail.foodId：菜品编号
     */
    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    /**
     * 获取：菜品名称
     * 表字段：foodorderdetail.foodName
     *
     * @return foodorderdetail.foodName：菜品名称
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * 设置：菜品名称
     * 表字段：foodorderdetail.foodName
     *
     * @param foodName
     *            foodorderdetail.foodName：菜品名称
     */
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    /**
     * 获取：数量
     * 表字段：foodorderdetail.orderCount
     *
     * @return foodorderdetail.orderCount：数量
     */
    public Integer getOrderCount() {
        return orderCount;
    }

    /**
     * 设置：数量
     * 表字段：foodorderdetail.orderCount
     *
     * @param orderCount
     *            foodorderdetail.orderCount：数量
     */
    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    /**
     * 获取：菜品状态 0 购物车 1待支付 2待后台支付 3后台确认支付 4制作中 5已派送 6已评价 7 退款中 8 已退款
     * 表字段：foodorderdetail.orderState
     *
     * @return foodorderdetail.orderState：菜品状态 0 购物车 1待支付 2待后台支付 3后台确认支付 4制作中 5已派送 6已评价 7 退款中 8 已退款
     */
    public Integer getOrderState() {
        return orderState;
    }

    /**
     * 设置：菜品状态 0 购物车 1待支付 2待后台支付 3后台确认支付 4制作中 5已派送 6已评价 7 退款中 8 已退款
     * 表字段：foodorderdetail.orderState
     *
     * @param orderState
     *            foodorderdetail.orderState：菜品状态 0 购物车 1待支付 2待后台支付 3后台确认支付 4制作中 5已派送 6已评价 7 退款中 8 已退款
     */
    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    /**
     * 获取：备注
     * 表字段：foodorderdetail.remark
     *
     * @return foodorderdetail.remark：备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：备注
     * 表字段：foodorderdetail.remark
     *
     * @param remark
     *            foodorderdetail.remark：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：创建时间
     * 表字段：foodorderdetail.createTime
     *
     * @return foodorderdetail.createTime：创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置：创建时间
     * 表字段：foodorderdetail.createTime
     *
     * @param createTime
     *            foodorderdetail.createTime：创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：失效时长
     * 表字段：foodorderdetail.closeSpaceTime
     *
     * @return foodorderdetail.closeSpaceTime：失效时长
     */
    public Integer getCloseSpaceTime() {
        return closeSpaceTime;
    }

    /**
     * 设置：失效时长
     * 表字段：foodorderdetail.closeSpaceTime
     *
     * @param closeSpaceTime
     *            foodorderdetail.closeSpaceTime：失效时长
     */
    public void setCloseSpaceTime(Integer closeSpaceTime) {
        this.closeSpaceTime = closeSpaceTime;
    }

    /**
     * 获取：0有效 1无效
     * 表字段：foodorderdetail.dataFlag
     *
     * @return foodorderdetail.dataFlag：0有效 1无效
     */
    public Integer getDataFlag() {
        return dataFlag;
    }

    /**
     * 设置：0有效 1无效
     * 表字段：foodorderdetail.dataFlag
     *
     * @param dataFlag
     *            foodorderdetail.dataFlag：0有效 1无效
     */
    public void setDataFlag(Integer dataFlag) {
        this.dataFlag = dataFlag;
    }

    /**
     * 获取：单件价格
     * 表字段：foodorderdetail.singlePrice
     *
     * @return foodorderdetail.singlePrice：单件价格
     */
    public Float getSinglePrice() {
        return singlePrice;
    }

    /**
     * 设置：单件价格
     * 表字段：foodorderdetail.singlePrice
     *
     * @param singlePrice
     *            foodorderdetail.singlePrice：单件价格
     */
    public void setSinglePrice(Float singlePrice) {
        this.singlePrice = singlePrice;
    }

    /**
     * 获取：单品价格
     * 表字段：foodorderdetail.total
     *
     * @return foodorderdetail.total：单品价格
     */
    public Float getTotal() {
        return total;
    }

    /**
     * 设置：单品价格
     * 表字段：foodorderdetail.total
     *
     * @param total
     *            foodorderdetail.total：单品价格
     */
    public void setTotal(Float total) {
        this.total = total;
    }

    /**
     * 获取：更新时间
     * 表字段：foodorderdetail.updateTime
     *
     * @return foodorderdetail.updateTime：更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置：更新时间
     * 表字段：foodorderdetail.updateTime
     *
     * @param updateTime
     *            foodorderdetail.updateTime：更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}