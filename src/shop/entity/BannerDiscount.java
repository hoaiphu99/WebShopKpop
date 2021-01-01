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
@Table(name="BannerDiscount")
public class BannerDiscount {
	@Id
	@GeneratedValue
	private Integer Id;
	
	private String SmallTitle;
	private String BigTitle;
	private Integer Discount;
	private String Image;
	private String Link;
	private Integer Day;
	private Integer Month;
	private Integer Year;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="DD/mm/yyyy")
	private Date Created;
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		Link = link;
	}

	public Integer getDay() {
		return Day;
	}

	public void setDay(Integer day) {
		Day = day;
	}
	
	public Integer getMonth() {
		return Month;
	}

	public void setMonth(Integer month) {
		Month = month;
	}

	public Integer getYear() {
		return Year;
	}

	public void setYear(Integer year) {
		Year = year;
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

	public Integer getDiscount() {
		return Discount;
	}

	public void setDiscount(Integer discount) {
		Discount = discount;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public Date getCreated() {
		return Created;
	}

	public void setCreated(Date created) {
		Created = created;
	}
	
	
	
}
