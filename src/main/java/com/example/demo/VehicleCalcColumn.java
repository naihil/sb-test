package com.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "vehicle_calc_column")
@SequenceGenerator(name = "vehicle_calc_column_seq", sequenceName = "vehicle_calc_column_seq", allocationSize = 5)
@Getter
@Setter
@ToString
public class VehicleCalcColumn {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_calc_column_seq")
	@Column(name = "id", updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;

	@Column(name = "title")
	private String title;

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy
				? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
				: o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy
				? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
				: this.getClass();
		if (thisEffectiveClass != oEffectiveClass) return false;
		VehicleCalcColumn ovcc = (VehicleCalcColumn) o;
		return getId() != null && Objects.equals(getId(), ovcc.getId());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy
				? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
				: getClass().hashCode();
	}
}
