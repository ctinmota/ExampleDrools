package com.cmota.example.template;

public class Item {

	private String name;
	private int weight;
	private int price;
	private ItemCode code;

	public Item(String n, int p, int w, ItemCode c) {
		name = n;
		price = p;
		weight = w;
		code = c;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public ItemCode getCode() {
		return code;
	}

	public void setCode(ItemCode code) {
		this.code = code;
	}

}
