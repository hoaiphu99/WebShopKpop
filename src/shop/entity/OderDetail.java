package shop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OderDetails")
public class OderDetail implements Serializable{

	@Id
	@ManyToOne
	@JoinColumn(name="OderID")
	private Oder oder;
	@Id
	@ManyToOne
	@JoinColumn(name="ProdID")
	private Product product;
	
	private Integer Quantity;
	private Double UnitPrice;
	
	public Oder getOder() {
		return oder;
	}
	public void setOder(Oder oder) {
		this.oder = oder;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return Quantity;
	}
	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}
	public Double getUnitPrice() {
		return UnitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		UnitPrice = unitPrice;
	}
	
	
	
	
	
}
