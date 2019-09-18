package cn.ffcs.controller;

import java.util.List;

import cn.ffcs.bean.FoodOrderRecords;
import cn.ffcs.constant.Constants;
import cn.ffcs.service.FoodOrderDetailService;
import cn.ffcs.service.FoodOrderRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.ffcs.bean.FoodOrderDetail;
import cn.ffcs.bean.User;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("loginUser")
@RequestMapping("/foodOrderDetail")
public class FoodOrderDetailController {
    @Autowired
    private FoodOrderDetailService foodOrderDetailService;

    @Autowired
    private FoodOrderRecordsService foodOrderRecordsService;

    /**
     * 进入购物车
     *
     * @param map
     * @return
     */
    @RequestMapping("/shoppingCar")
    public String ShoppingCar(ModelMap map) {
        User loginUser = (User) map.get("loginUser");
        if (loginUser == null) {
            return "userLogin";
        }
        int userId = loginUser.getId();
        List <FoodOrderDetail> FoodOrderDetailList = foodOrderDetailService.queryFoodOrderDetailListByUserId(userId);
        float money = foodOrderDetailService.sumMoney(userId, 0, Constants.ORDER_SHOPPING_CART);
        if (money > 0) {
            map.put("money", money + " ￥");
        }
        map.put("myShoppingCarFoodList", FoodOrderDetailList);
        return "shoppingCar";
    }

    /**
     * 加入购物车
     *
     * @param foodId
     * @param map
     * @return
     */
    @RequestMapping("/doAddShoppingCar")
    @ResponseBody
    public String doAddShoppingCar(@RequestParam("foodId") int foodId, ModelMap map) {
        User loginUser = (User) map.get("loginUser");
        if (loginUser == null) {
            return "userLogin";
        }
        int userId = loginUser.getId();
        return foodOrderDetailService.doAddShoppingCar(foodId, userId) == true ? "加入成功" : "加入失败";
    }

    /**
     * 操作购物车
     *
     * @param map
     * @param operateId
     * @param operate
     * @return
     */
    @RequestMapping("/operateCar")
    @ResponseBody
    public String operateCar(ModelMap map, @RequestParam("operateId") int operateId,
                             @RequestParam("operate") int operate) {
        User loginUser = (User) map.get("loginUser");
        int userId = loginUser.getId();
        boolean result = foodOrderDetailService.operateCar(operateId, userId, operate);
        String data = result == true ? "操作成功" : "操作失败";
        return data;
    }

    /**
     * 支付
     *
     * @param map
     * @return
     */
    @RequestMapping("/doPay")
    @ResponseBody
    public String doPay(ModelMap map) {
        User loginUser = (User) map.get("loginUser");
        return foodOrderDetailService.doPay(loginUser.getId()) == true ? "支付成功" : "支付失败";
    }

    /**
     * 订单中心
     *
     * @param map
     * @return
     */
    @RequestMapping("/orderCenter")
    public String orderCenter(ModelMap map) {
        User loginUser = (User) map.get("loginUser");
        if (loginUser == null) {
            return "userLogin";
        }
        int userId = loginUser.getId();
        List <FoodOrderRecords> foodOrderRecords = foodOrderRecordsService.selectByUserId(userId);
        map.put("foodOrderRecordsList", foodOrderRecords);
        return "orderCenter";
    }

    /**
     * 订单详细
     *
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/orderDetail")
    public String orderDetail(HttpServletRequest request, ModelMap map) {
        int recordsId = Integer.parseInt(request.getParameter("id"));
        User loginUser = (User) map.get("loginUser");
        int userId = loginUser.getId();

        System.out.println(recordsId);

        List <FoodOrderDetail> FoodOrderDetailList = foodOrderDetailService
                .queryFoodOrderDetailListByRecordsNo(userId, recordsId);
        float money = foodOrderDetailService.sumMoney(userId, recordsId, Constants.ORDER_PAYMENTED);
        if (money > 0) {
            map.put("money", money + " ￥");
        }
        map.put("myShoppingCarFoodList", FoodOrderDetailList);
        return "orderDetail";
    }

    /**
     * 取消订单
     *
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/cancelOrder")
    @ResponseBody
    public String cancelOrder(HttpServletRequest request, ModelMap map) {
        int id = Integer.parseInt(request.getParameter("id"));
        User loginUser = (User) map.get("loginUser");
        int userId = loginUser.getId();
        foodOrderRecordsService.updateRecords(userId, id, Constants.ORDER_REFUNDING);
        return "取消成功";
    }

}
