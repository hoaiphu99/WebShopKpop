package shop.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="SliderDiscount")
public class SliderDiscount {
	@Id
	@GeneratedValue
	private Integer Id;
	
	private String SmallTitle;
	private String BigTitle;
	private String Link;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="DD/mm/yyyy")
	private Date Created;
	
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getSmallTitle() {
		return SmallTitle;
	}
	public void setSmallTitle(String smallTitle) {
		SmallTitle = smallTitle;
	}
	public String getBigTitle() {
		return BigTitle;
	}
	public void setBigTitle(String bigTitle) {
		BigTitle = bigTitle;
	}
	public String getLink() {
		return Link;
	}
	public void setLink(String link) {
		Link = link;
	}
	public Date getCreated() {
		return Created;
	}
	public void setCreated(Date created) {
		Created = created;
	}
	
	
}
