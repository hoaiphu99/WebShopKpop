package shop.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Status")
public class Status {
	@Id
	@GeneratedValue
	private Integer Id;
	private String Name;
	
	@OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
	private Collection<Order> oders;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Collection<Order> getOders() {
		return oders;
	}
	public void setOders(Collection<Order> oders) {
		this.oders = oders;
	}
	
	
}
