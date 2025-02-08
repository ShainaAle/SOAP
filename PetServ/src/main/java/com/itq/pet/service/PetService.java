package com.itq.pet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.itq.pet.dto.Pet;

@Service
public class PetService {
	private List<Pet> petList;
	private static final Logger logger = LoggerFactory.getLogger(PetService.class);
	
	public PetService() {
		petList = new ArrayList<>();
		Pet pet1 = new Pet("M-001", "C-001", "Ruth", "Pitbull", (int)4, (float)35);
		Pet pet2 = new Pet("M-002", "C-002", "Max", "Chihuahua", (int)3, (float)40.1);
		petList.add(pet1);
		petList.add(pet2);	
	}
	
	public Optional<Pet> getPet(String idPet) {
	    return petList.stream()
	                  .filter(pet -> pet.getIdPet().equals(idPet))
	                  .findFirst()
	                  .or(() -> {
	                      logger.warn("Mascota " + idPet + " no encontrada.");
	                      return Optional.empty();
	                  });
	}
	
	public class PetYaExisteException extends RuntimeException{
		public PetYaExisteException(String mensaje) {
			super(mensaje);
		}
	}
	
	public void addPet(Pet pet) {
		for(Pet p : petList) {
			if(p.getIdPet().equals(pet.getIdPet())) {
				throw new PetYaExisteException("La mascota " + pet.getIdPet() + " ya existe.");
			}
		}
		
		petList.add(pet);
		logger.info("Mascota " + pet.getIdPet() + " registrada.");
	}
	
	public class PetNotFoundExcep extends RuntimeException{
		public PetNotFoundExcep(String mensaje) {
			super(mensaje);
		}
	}
	
	public void updatePet(Pet pet) {
	    Pet updatePet = petList.stream()
	                           .filter(p -> p.getIdPet().equals(pet.getIdPet()))
	                           .findFirst()
	                           .orElseThrow(() -> new PetNotFoundExcep("Mascota " + pet.getIdPet() + " no encontrada."));

	    // Actualiza los valores de la mascota encontrada
	    updatePet.setPetAge(pet.getPetAge());
	    updatePet.setDogWeight(pet.getDogWeight());
	    updatePet.setPetName(pet.getPetName());
	    updatePet.setPetBreed(pet.getPetBreed());
	}
	
	public List<Pet> getAllPets(){
		logger.warn("Se han solicitado todas las reservaciones");
		return petList;
	}

	
	public boolean deletePet(String idPet) {
		for(int i = 0; i < petList.size(); i++) {
			if(petList.get(i).getIdPet().equals(idPet)) {
				petList.remove(i);
				logger.warn("Mascota " + idPet + " eliminada");
				return true;
			}
		}
		logger.warn("Mascota no encontrada");
		return false;
	}
}
