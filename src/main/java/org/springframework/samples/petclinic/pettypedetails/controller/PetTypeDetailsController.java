package org.springframework.samples.petclinic.pettypedetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.pettypedetails.dto.PetTypeDetailsDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;


import org.springframework.samples.petclinic.pettypedetails.model.PetTypeDetails;
import org.springframework.samples.petclinic.pettypedetails.service.PetTypeDetailsService;

import java.util.List;


@RestController
@RequestMapping("/api/pet-details")
public class PetTypeDetailsController {

	@Autowired
	private PetTypeDetailsService petTypeDetailsService;

	@GetMapping
	public ResponseEntity<Iterable<PetTypeDetails>> getDetails() {
		return ResponseEntity.ok(petTypeDetailsService.getAll());
	}

	@PostMapping
	public ResponseEntity<PetTypeDetails> createDetails(@RequestBody PetTypeDetailsDTO petTypeDetailsDTO) {
		return ResponseEntity.ok(petTypeDetailsService.createDetails(petTypeDetailsDTO));
	}

	@GetMapping("/{petId}")
	public ResponseEntity<PetTypeDetails> getDetails(@PathVariable Integer petId) {
		return petTypeDetailsService.getDetails(petId)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}
}
