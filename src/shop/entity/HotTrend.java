package shop.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="HotTrend")
public class HotTrend {
	@Id
	@GeneratedValue
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name="ProdID")
	private Product product;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="DD/mm/yyyy")
	private Date Created;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getCreated() {
		return Created;
	}

	public void setCreated(Date created) {
		Created = created;
	}
	
	
}
