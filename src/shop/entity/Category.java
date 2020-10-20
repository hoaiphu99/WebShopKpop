package shop.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

@Entity
@Table(name="Category")
public class Category {
	@Id
	@GeneratedValue()
	private String Id;
	private String Name;
	
	@OneToMany(mappedBy="category", fetch=FetchType.EAGER)
	private Collection<Product> products;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> product) {
		this.products = product;
	}
	
	
}
