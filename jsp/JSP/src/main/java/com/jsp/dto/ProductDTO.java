package com.jsp.dto;

public class ProductDTO {
	private String prodName;
	private int prodNum;
	private int price;
	private String color;
	
	public ProductDTO(String prodName, int prodNum, int price, String color) {
		super();
		this.prodName = prodName;
		this.prodNum = prodNum;
		this.price = price;
		this.color = color;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getProdNum() {
		return prodNum;
	}

	public void setProdNum(int prodNum) {
		this.prodNum = prodNum;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "ProductDTO [prodName=" + prodName + ", prodNum=" + prodNum + ", price=" + price + ", color=" + color
				+ "]";
	}
}
