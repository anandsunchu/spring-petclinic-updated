package org.springframework.samples.petclinic.pettypedetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetType;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {

}
