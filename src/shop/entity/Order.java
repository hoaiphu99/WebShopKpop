package shop.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Orders")
public class Order {

	@Id
	@GeneratedValue
	private Integer Id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="DD/mm/yyyy")
	private Date Created;
	
	private Double TotalPrice;
	private Integer TotalQuantity;
	
	@ManyToOne
	@JoinColumn(name="UserID")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="StatusID")
	private Status status;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	private Collection<OrderDetail> orderdetails;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Date getCreated() {
		return Created;
	}

	public void setCreated(Date created) {
		Created = created;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Collection<OrderDetail> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(Collection<OrderDetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public Double getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		TotalPrice = totalPrice;
	}

	public Integer getTotalQuantity() {
		return TotalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		TotalQuantity = totalQuantity;
	}
	
	
}
