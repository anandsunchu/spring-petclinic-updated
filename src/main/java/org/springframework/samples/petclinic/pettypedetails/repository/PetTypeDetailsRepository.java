package org.springframework.samples.petclinic.pettypedetails.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetType;
import org.springframework.samples.petclinic.pettypedetails.model.PetTypeDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetTypeDetailsRepository extends CrudRepository<PetTypeDetails, Integer> {
	Optional<PetTypeDetails> findByPet(Pet pet);
}
