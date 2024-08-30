package com.spring.domain;

public class ProductDTO {
	private String prodNo;
	private String prodName;
	private int price;

	public ProductDTO(String prodNo, String prodName, int price) {
		super();
		this.prodNo = prodNo;
		this.prodName = prodName;
		this.price = price;
	}

	public ProductDTO() {
		super();
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductDTO [prodNo=" + prodNo + ", prodName=" + prodName + ", price=" + price + "]";
	}
}
