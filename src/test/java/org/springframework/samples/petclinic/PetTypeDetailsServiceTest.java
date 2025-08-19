package org.springframework.samples.petclinic;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.pettypedetails.dto.PetTypeDetailsDTO;
import org.springframework.samples.petclinic.pettypedetails.model.PetTypeDetails;
import org.springframework.samples.petclinic.pettypedetails.repository.PetRepository;
import org.springframework.samples.petclinic.pettypedetails.repository.PetTypeDetailsRepository;
import org.springframework.samples.petclinic.pettypedetails.service.PetTypeDetailsService;

public class PetTypeDetailsServiceTest {

	@Mock
	private PetTypeDetailsRepository petTypeDetailsRepository;

	@Mock
	private PetRepository petRepository;

	@InjectMocks
	private PetTypeDetailsService petTypeDetailsService;

	private Pet pet;
	private PetTypeDetailsDTO detailsDto;
	private PetTypeDetails petTypeDetails;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		pet = new Pet();
		pet.setId(1);
		pet.setName("Tommy");

		detailsDto = new PetTypeDetailsDTO();
		detailsDto.setPetId(1);
		detailsDto.setTemperament("Playful");
		detailsDto.setLength(50);
		detailsDto.setWeight(10);

		petTypeDetails = new PetTypeDetails();
		petTypeDetails.setId(1);
		petTypeDetails.setPet(pet);
		petTypeDetails.setTemperament("Playful");
		petTypeDetails.setLength(50);
		petTypeDetails.setWeight(10);
	}

	@Test
	void testCreateDetails_New() {
		when(petRepository.findById(1)).thenReturn(Optional.of(pet));
		when(petTypeDetailsRepository.findById(1)).thenReturn(Optional.empty());
		when(petTypeDetailsRepository.save(any(PetTypeDetails.class))).thenReturn(petTypeDetails);

		PetTypeDetails result = petTypeDetailsService.createDetails(detailsDto);

		assertNotNull(result);
		assertEquals("Playful", result.getTemperament());
		verify(petRepository, times(1)).findById(1);
		verify(petTypeDetailsRepository, times(1)).save(any(PetTypeDetails.class));
	}

	@Test
	void testCreateDetails_UpdateExisting() {
		when(petRepository.findById(1)).thenReturn(Optional.of(pet));
		when(petTypeDetailsRepository.findById(1)).thenReturn(Optional.of(petTypeDetails));
		when(petTypeDetailsRepository.save(any(PetTypeDetails.class))).thenReturn(petTypeDetails);

		detailsDto.setTemperament("Aggressive");

		PetTypeDetails result = petTypeDetailsService.createDetails(detailsDto);

		assertEquals("Aggressive", result.getTemperament());
		verify(petTypeDetailsRepository, times(1)).save(petTypeDetails);
	}

	@Test
	void testCreateDetails_PetNotFound() {
		when(petRepository.findById(1)).thenReturn(Optional.empty());

		RuntimeException ex = assertThrows(RuntimeException.class,
			() -> petTypeDetailsService.createDetails(detailsDto));

		assertEquals("Pet not found", ex.getMessage());
	}

	@Test
	void testGetDetails_Found() {
		when(petRepository.findById(1)).thenReturn(Optional.of(pet));
		when(petTypeDetailsRepository.findByPet(pet)).thenReturn(Optional.of(petTypeDetails));

		Optional<PetTypeDetails> result = petTypeDetailsService.getDetails(1);

		assertTrue(result.isPresent());
		assertEquals("Playful", result.get().getTemperament());
	}

	@Test
	void testGetDetails_NotFound() {
		when(petRepository.findById(1)).thenReturn(Optional.of(pet));
		when(petTypeDetailsRepository.findByPet(pet)).thenReturn(Optional.empty());

		Optional<PetTypeDetails> result = petTypeDetailsService.getDetails(1);

		assertFalse(result.isPresent());
	}
}
