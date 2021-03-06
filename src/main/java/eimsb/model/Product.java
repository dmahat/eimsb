package eimsb.model;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * Simple Domain Representation for a product.
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	public Product(String id) {
		super();
		this.id = id;
	}

	public Product() {
	}

	private String id;
	private String name;
	private String description;
	private BigDecimal price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return id + "," + name + "," + description + "," + price;
	}

}
