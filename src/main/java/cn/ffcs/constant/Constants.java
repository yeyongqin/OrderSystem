package cn.ffcs.constant;
public class Constants {
    //----------------------订单状态--------------------------
    /**
     * 购物车
     */
    public static final int ORDER_SHOPPING_CART = 0;
    /**
     * 已支付
     */
    public static final int ORDER_PAYMENTED = 4;
    /**
     * 退款中
     */
    public static final int ORDER_REFUNDING = 7;
    /**
     * 已退款
     */
    public static final int ORDER_REFUNDED = 8;

    //----------------------数据有效性--------------------------
    /**
     * 有效数据
     */
    public static final int DATA_FLAG_0 = 0;
    /**
     * 无效数据
     */
    public static final int DATA_FLAG_1 = 1;

    //----------------------订单编号--------------------------
    /**
     * 未生成订单的订单编号为0
     */
    public static final int RECORDS_ID_0 = 0;

    public static String orderStateNumToCHN(int orderState) {
        if (orderState == Constants.ORDER_SHOPPING_CART) {
            return "购物车";
        } else if (orderState == Constants.ORDER_PAYMENTED) {
            return "已支付";
        } else if (orderState == Constants.ORDER_REFUNDING) {
            return "退款中";
        } else if (orderState == Constants.ORDER_REFUNDED) {
            return "已退款";
        } else {
            return "";
        }
    }
}
