package com.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle")
@SequenceGenerator(name = "vehicle_seq", sequenceName = "vehicle_seq", allocationSize = 5)
@Getter
@Setter
@ToString
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_seq")
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "updated_at")
	private OffsetDateTime updatedAt;

	@Column(name = "reg_num")
	private String regNum;

	@OneToMany(
			mappedBy = "vehicle",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@ToString.Exclude
	private List<VehicleCalcColumn> calcColumns = new ArrayList<>();

	public void addCalcColumn(VehicleCalcColumn vehicleCalcColumn) {
		calcColumns.add(vehicleCalcColumn);
		vehicleCalcColumn.setVehicle(this);
	}

	public void delCalcColumn(VehicleCalcColumn vehicleCalcColumn) {
		calcColumns.remove(vehicleCalcColumn);
		vehicleCalcColumn.setVehicle(null);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Vehicle)) return false;
		return id != null && id.equals(((Vehicle) o).getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
