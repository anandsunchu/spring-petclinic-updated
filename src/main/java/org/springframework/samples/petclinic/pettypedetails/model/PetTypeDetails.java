package org.springframework.samples.petclinic.pettypedetails.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.owner.Pet;


@Entity
@Table(name = "pet_type_details")
public class PetTypeDetails extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "pet_id", nullable = false, unique = true)
	@JsonIgnore
	private Pet pet;

	@Column(name = "temperament", length = 64)
	private String temperament;


	@Column(name = "length")
	private Integer length;


	@Column(name = "weight")
	private Integer weight;

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public String getTemperament() {
		return temperament;
	}

	public void setTemperament(String temperament) {
		this.temperament = temperament;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}
