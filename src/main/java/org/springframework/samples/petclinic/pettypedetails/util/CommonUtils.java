package org.springframework.samples.petclinic.pettypedetails.util;

import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.pettypedetails.dto.PetTypeDetailsDTO;
import org.springframework.samples.petclinic.pettypedetails.model.PetTypeDetails;

public class CommonUtils {
	public static PetTypeDetails mapPetDetailsDTOtoEntity(PetTypeDetailsDTO petTypeDetailsDTO, Pet pet){
		PetTypeDetails details = new PetTypeDetails();
		details.setPet(pet);
		details.setTemperament(petTypeDetailsDTO.getTemperament());
		details.setLength(petTypeDetailsDTO.getLength());
		details.setWeight(petTypeDetailsDTO.getWeight());
		return details;
	}
}
