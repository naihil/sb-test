package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VehicleCalcColumnController {
	private final VehicleCalcColumnService vehicleCalcColumnService;

	@PostMapping("/vehicleCalcColumns/")
	public VehicleCalcColumnGetDto create(@RequestBody VehicleCalcColumnCreateDto input) {
		return vehicleCalcColumnService.create(input);
	}
}
