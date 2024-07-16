package com.nt.Model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component // Assuming it's managed by Spring container
public class Order {
	
	private Integer id; // Follow Java naming conventions
	private String name;
	private String email;
	private List<Items> items; // Renamed to follow Java naming conventions
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Items> getItems() {
		return items;
	}
	
	public void setItems(List<Items> items) {
		this.items = items;
	}
	
	public Order() {
		// Default constructor
	}
	
	public Order(Integer id, String name, String email, List<Items> items) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.items = items;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", email=" + email + ", items=" + items + "]";
	}
	
	// Optionally override equals() and hashCode() if needed
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return id.equals(other.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
