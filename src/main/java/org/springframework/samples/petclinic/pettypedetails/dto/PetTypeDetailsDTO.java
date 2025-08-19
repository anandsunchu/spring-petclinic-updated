package org.springframework.samples.petclinic.pettypedetails.dto;

import org.springframework.stereotype.Component;

@Component
public class PetTypeDetailsDTO {
	private Integer petId;
	private String temperament;
	private Integer length;
	private Integer weight;

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
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
