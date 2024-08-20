package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleCalcColumnService {
	private final VehicleRepository vehicleRepository;

	@Transactional(propagation = Propagation.REQUIRED)
	public VehicleCalcColumnGetDto create(VehicleCalcColumnCreateDto input) {
		log.info("[create][REQUEST] input[{}]", input);
		final VehicleCalcColumn vehicleCalcColumn = new VehicleCalcColumn();
		vehicleCalcColumn.setTitle(input.title());

		Vehicle vehicle = vehicleRepository.findById(input.vehicleId()).orElseThrow();
		vehicle.addCalcColumn(vehicleCalcColumn);
		vehicle.setUpdatedAt(OffsetDateTime.now());
		vehicle = vehicleRepository.save(vehicle);

		log.info("[create] after vehicle save: {}", vehicle.getCalcColumns());

		final VehicleCalcColumnGetDto result = new VehicleCalcColumnGetDto(vehicleCalcColumn.getId(), vehicleCalcColumn.getTitle());

		log.info("[create][RESULT] {}", result);
		return result;
	}
}
