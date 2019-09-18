package cn.ffcs.controller;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.ffcs.service.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.ffcs.bean.Food;
import cn.ffcs.bean.FoodType;
import cn.ffcs.bean.custom.AddNewFoodPoJo;
import cn.ffcs.service.FoodService;
import cn.ffcs.util.OrderUtil;

@Controller
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private FoodTypeService foodTypeService;

    /**
     * 菜品管理
     *
     * @param map
     * @return
     */
    @RequestMapping("/manageFood")
    public String manageFood(ModelMap map) {
        List <Food> allFoodList = foodService.queryAllFood();
        map.put("allFoodList", allFoodList);
        return "manageFood";
    }

    /**
     * 添加新菜品
     *
     * @param request
     * @param addNewFoodPoJO
     * @return
     */
    @RequestMapping("/addNewFood")
    @ResponseBody
    public String addNewFood(HttpServletRequest request, @ModelAttribute AddNewFoodPoJo addNewFoodPoJO) {
        return "上传成功";
    }

    /**
     * 删除菜品
     *
     * @param foodId
     * @return
     */
    @RequestMapping("/deleteFood")
    @ResponseBody
    public String doDeleteFood(@RequestParam("foodId") int foodId) {
        Food food = new Food();
        food.setId(foodId);
        food.setDataFlag(1);
        return foodService.updateFood(food) == true ? "删除成功" : "删除失败";
    }

}
