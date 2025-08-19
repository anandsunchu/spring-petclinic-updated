package org.springframework.samples.petclinic.pettypedetails.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.pettypedetails.dto.PetTypeDetailsDTO;
import org.springframework.samples.petclinic.pettypedetails.model.PetTypeDetails;
import org.springframework.samples.petclinic.pettypedetails.repository.PetRepository;
import org.springframework.samples.petclinic.pettypedetails.repository.PetTypeDetailsRepository;
import org.springframework.samples.petclinic.pettypedetails.util.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
public class PetTypeDetailsService{

	@Autowired
	private PetTypeDetailsRepository petTypeDetailsRepository;
	@Autowired
	private PetRepository petRepository;

//	public PetTypeDetails createOrUpdate(PetTypeDetailsDTO petTypeDetailsDTO) {
//		PetType petType = petTypeRepo.findById(petTypeDetailsDTO.getPetTypeId())
//			.orElseThrow(() -> new IllegalArgumentException("PetType not found: " + petTypeDetailsDTO.getPetTypeId()));
//
//		return petTypeDetailsRepository.save(CommonUtils.mapPetTypeDTOtoEntity(petTypeDetailsDTO, petType));
//	}
//
//	public Optional<PetTypeDetails> getById(Integer petTypeDetailsId) {
//		return petTypeDetailsRepository.findById(petTypeDetailsId);
//	}

	public Iterable<PetTypeDetails> getAll() {
		return petTypeDetailsRepository.findAll();
	}

	public PetTypeDetails createDetails(PetTypeDetailsDTO details) {
		Pet pet = petRepository.findById(details.getPetId())
			.orElseThrow(() -> new RuntimeException("Pet not found"));
		Optional<PetTypeDetails> existingPetTypeDetails = petTypeDetailsRepository.findById(details.getPetId());
		PetTypeDetails petTypeDetails;
		if (existingPetTypeDetails.isPresent()) {
			PetTypeDetails existingDetails = existingPetTypeDetails.get();
			existingDetails.setTemperament(details.getTemperament());
			existingDetails.setLength(details.getLength());
			existingDetails.setWeight(details.getWeight());
			return petTypeDetailsRepository.save(existingDetails);
		}else {
			petTypeDetails = CommonUtils.mapPetDetailsDTOtoEntity(details, pet);
			return petTypeDetailsRepository.save(petTypeDetails);
		}
	}

	public Optional<PetTypeDetails> getDetails(Integer petId) {
		Pet pet = petRepository.findById(petId)
			.orElseThrow(() -> new RuntimeException("Pet not found"));
		return petTypeDetailsRepository.findByPet(pet);
	}
}
