package cn.ffcs.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import cn.ffcs.bean.UserAccount;
import cn.ffcs.constant.Constants;
import cn.ffcs.util.SendSmsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.ffcs.bean.Food;
import cn.ffcs.bean.User;
import cn.ffcs.service.FoodService;
import cn.ffcs.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes({"loginUser", "code", "registerPhone"})
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    private static final String REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";

    /**
     * 公告
     *
     * @return
     */
    @RequestMapping("/toNotice")
    public String toNotice() {
        return "notice";
    }

    /**
     * 用户登录
     *
     * @param phone
     * @param userPassword
     * @param map
     * @return
     */
    @RequestMapping("/doUserLogin")
    public String doUserLogin(@RequestParam("phone") String phone
            , @RequestParam("userPassword") String userPassword, ModelMap map) {
        map.remove("error");

        User loginUser = userService.queryUserByPhoneAndPassword(phone, userPassword);
        if (loginUser == null) {
            map.put("error", "用户名或密码错误");
            return "userLogin";
        }

        map.put("loginUser", loginUser);
        return "redirect:/user/toCustomerCenter";
    }

    /**
     * 用户注册
     *
     * @param phone
     * @param password
     * @param validateCode
     * @param map
     * @return
     */
    @RequestMapping("/doUserRegister")
    @ResponseBody
    public String doUserRegister(@RequestParam("username") String phone
            , @RequestParam("password") String password
            , @RequestParam("password_PwdTwo") String passwordTwo
            , @RequestParam("name") String name
            , @RequestParam("validateCode") String validateCode, ModelMap map) {

        if (phone == null || phone.equals("")) {
            return "请输入手机号";
        } else {
            if (!Pattern.matches(REGEX, phone)) {
                return "手机号格式不正确";
            }
        }

        User user = userService.queryUserByPhone(phone);
        if (user != null) {
            return "该用户已被注册";
        }

        if (password == null || password.equals("")) {
            return "请输入密码";
        }

        if (!password.equals(passwordTwo)) {
            return "请确认两次密码相同";
        }

        if (name.length() > 10) {
            return "名字长度不能大于10个字符";
        }

//        if (map.get("code") == null) {
//            return "请先获取验证码";
//        }

//        if (!map.get("code").toString().equals(validateCode)) {
//            return "验证码错误";
//        }

        if (!map.get("registerPhone").toString().equals(phone)) {
            return "手机号错误";
        }

        int response = userService.addNewUser(phone, password, name);

        if (response > 0) {
            //查询最新的对象，放至进session进行登录
            User loginUser = userService.queryUserById(response);
            map.put("loginUser", loginUser);

            //为用户创建账户
            int accountResponse = userService.addNewUserAccount(loginUser.getId());
            if (accountResponse > 0) {
                return "";
            } else {
                map.remove("loginUser");
                userService.deleteUserByUserId(loginUser.getId());
                return "创建账户失败";
            }
        } else {
            return "网络异常注册失败";
        }
    }

    /**
     * 获取手机验证码
     *
     * @return
     */
    @RequestMapping("/ajaxGetVerifyCode")
    @ResponseBody
    public String ajaxGetVerifyCode(@RequestParam("username") String registerPhone, ModelMap map) {
        StringBuffer validateCode = new StringBuffer();
        //生成验证码
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            validateCode.append(random.nextInt(10));
        }

        //判断当前用户是否已经注册
        User user = userService.queryUserByPhone(registerPhone);
        if (user != null) {
            return "该用户已被注册";
        }
        //未注册
        else {
            try {
                SendSmsUtils.sendSMS(registerPhone, validateCode.toString());
                //验证码
                map.put("code", validateCode);
                //验证码对应的手机号
                map.put("registerPhone", registerPhone);
            } catch (IOException e) {
                e.printStackTrace();
                return "服务器异常";
            }
            return "";
        }
    }

    /**
     * 用户注销
     *
     * @param map
     * @return
     */
    @RequestMapping("/zhuxiao")
    public String userZhuXiao(ModelMap map) {
        map.remove("loginUser");
        return "userLogin";
    }

    /**
     * 客户中心
     *
     * @return
     */
    @RequestMapping("/toCustomerCenter")
    public String toCustomerCenter(ModelMap map) {
        User loginUser = (User) map.get("loginUser");
        if (loginUser == null) {
            return "userLogin";
        }
        int userId = loginUser.getId();
        UserAccount userAccount;
        String paymented = Constants.orderStateNumToCHN(Constants.ORDER_PAYMENTED);
        int pNum = userService.selectOrderNumByState(userId, paymented);
        String refunding = Constants.orderStateNumToCHN(Constants.ORDER_REFUNDING);
        int riNum = userService.selectOrderNumByState(userId, refunding);
        String refunded = Constants.orderStateNumToCHN(Constants.ORDER_REFUNDED);
        int rdNum = userService.selectOrderNumByState(userId, refunded);
        userAccount = userService.queryUserAccount(userId);
        map.put("pNum", pNum);
        map.put("riNum", riNum);
        map.put("rdNum", rdNum);
        map.put("deposit", userAccount.getDeposit());
        return "customerCenter";
    }

    /**
     * 客户登录页面
     *
     * @return
     */
    @RequestMapping("/toUserLogin")
    public String toUserLogin() {
        return "userLogin";
    }

    /**
     * 注册页面
     *
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String toIndex(ModelMap map) {
        logger.info("用户主页");
        //食客最爱
        List <Food> response = foodService.queryFavouriteFoodList();
        //食客最爱(Top4)
        List <Food> favouriteFoodList = new LinkedList <>();
        if (response.size() >= 4) {
            for (int i = 0; i <= 3; i++) {
                favouriteFoodList.add(response.get(i));
            }
        } else {
            for (int i = 0; i < response.size(); i++) {
                favouriteFoodList.add(response.get(i));
            }
        }

        //每日推荐
        List <Food> recomendFoodList = foodService.queryRecommendFoodList();
        // 菜谱
        List <Food> foodsList = foodService.queryAllFood();

        map.put("favouriteFoodList", favouriteFoodList);
        map.put("recomendFoodList", recomendFoodList);
        map.put("foodsList", foodsList);
        return "index";
    }

}
