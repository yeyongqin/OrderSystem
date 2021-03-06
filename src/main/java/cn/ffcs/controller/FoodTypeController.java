package cn.ffcs.controller;

import java.util.List;

import cn.ffcs.service.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ffcs.bean.FoodType;

@Controller
@RequestMapping("/foodType")
public class FoodTypeController {
    @Autowired
    private FoodTypeService foodTypeService;

    /**
     * 所有菜品类别
     *
     * @return
     */
    @RequestMapping("/allFoodType")
    public String foodType(ModelMap map) {
        System.out.println("所有菜品类别");
        List <FoodType> allFoodTypeList = foodTypeService.queryAllFoodType();
        map.put("allFoodTypeList", allFoodTypeList);
        return "addNewFood";
    }

    /**
     * 添加菜品类别
     *
     * @return
     */
    @RequestMapping("/addFoodType")
    public String addFoodType(ModelMap map) {
        System.out.println("所有菜品类别");
        List <FoodType> addAllFoodTypeList = foodTypeService.queryAllFoodType();
        map.put("addAllFoodTypeList", addAllFoodTypeList);
        return "addFoodType";
    }

    /**
     * 添加菜品类别
     *
     * @param map
     * @return
     */
    @RequestMapping("/addNewFoodType")
    @ResponseBody
    public String addNewFoodType(ModelMap map, @RequestParam("typeName") String typeName) {
        System.out.println("addNewFoodType");
        return foodTypeService.addNewFoodType(typeName) == true ? "添加成功" : "添加失败";
    }
}
