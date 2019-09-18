package cn.ffcs.bean.custom;

import lombok.Data;

@Data
public class AddNewFoodPoJo {
  
	private int foodTypeId;
	private String foodName;

	public int getFoodTypeId() {
		return foodTypeId;
	}

	public void setFoodTypeId(int foodTypeId) {
		this.foodTypeId = foodTypeId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getSigniture() {
		return signiture;
	}

	public void setSigniture(int signiture) {
		this.signiture = signiture;
	}

	private Float price;
	private int recommend;
	private int signiture;
}
