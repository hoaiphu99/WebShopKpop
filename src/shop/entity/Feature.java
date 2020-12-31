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
@Table(name="Feature")
public class Feature {
	@Id
	@GeneratedValue
	private Integer Id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date DateStart;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date DateEnd;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Date getDateStart() {
		return DateStart;
	}

	public void setDateStart(Date dateStart) {
		DateStart = dateStart;
	}

	public Date getDateEnd() {
		return DateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		DateEnd = dateEnd;
	}
	
	
}
