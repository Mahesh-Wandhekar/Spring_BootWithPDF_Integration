package com.nt.Model; // Follow Java naming conventions for package names

public class Items {
	
	private String itemName;
	private Integer quantity; // Corrected spelling from quntity to quantity
	private double unitPrice;
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "Items [itemName=" + itemName + ", quantity=" + quantity + ", unitPrice=" + unitPrice + "]";
	}

	public Items(String itemName, Integer quantity, double unitPrice) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
}
