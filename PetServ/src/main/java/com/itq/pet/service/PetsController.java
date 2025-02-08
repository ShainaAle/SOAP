package com.itq.pet.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itq.pet.dto.Pet;
import com.itq.pet.service.PetService.PetNotFoundExcep;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Pet")
public class PetsController {
	
	private List<Pet> petList;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PetsController.class);
	
	@Autowired
		private PetService petService;
	
	public PetsController(PetService petService) {
		this.petService =petService;
	}
	
	@PostMapping("/addPet")
	public ResponseEntity<?> postPet(@Valid @RequestBody Pet newPet){
		try {
			petService.addPet(newPet);
			LOGGER.info("Mascota registrada exitosamente: {" + newPet +"}");
			return ResponseEntity.ok("Mascota registrada con exito: {" + newPet +"}" );
		}catch(Exception e) {
			LOGGER.info("Error al registrar la mascota: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar mascota: " + e.getMessage());
		}
	}
	
	@GetMapping("/{IdPet}")
	public ResponseEntity<?> getPet(@PathVariable String IdPet) {
	    Optional<Pet> pet = petService.getPet(IdPet);
	    LOGGER.info("Inicio de búsqueda. Id: " + IdPet);
	    if (pet.isPresent()) {
	        return ResponseEntity.ok(pet.get());
	    } else {
	        LOGGER.info("Mascota con Id " + IdPet + " no encontrada");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mascota no encontrada");
	    }
	}

	@GetMapping("/getPets")
	public ResponseEntity<?> readPet() {
		List<Pet> pets = petService.getAllPets();
			if(!pets.isEmpty()) {
				LOGGER.info("Todas las mascotas fueron mostradas.");
				return ResponseEntity.ok(pets);
			}
			else {
				LOGGER.info("Error, no hay mascotas para mostrar.");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay mascotas para mostrar.");
			}
	}
	
	@PutMapping("/updatePet/{IdPet}")
	public ResponseEntity<?> updatePet(@PathVariable String IdPet, @RequestBody Pet updatePet) {
	    try {
	        // Establece el ID desde la URL al objeto Pet
	        updatePet.setIdPet(IdPet);
	        petService.updatePet(updatePet);
	        LOGGER.info("Update request recibido {" + updatePet + "}");
	        return ResponseEntity.ok("Mascota actualizada");
	    } catch (PetNotFoundExcep e) {
	        LOGGER.info("Error al actualizar mascota: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al actualizar mascota: " + e.getMessage());
	    } catch (Exception e) {
	        LOGGER.info("Error inesperado al actualizar mascota: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar mascota: " + e.getMessage());
	    }
	}

	
	@DeleteMapping("/deletePet/{IdPet}")
	public ResponseEntity<?> deletePet(@PathVariable String IdPet) {
	    boolean deleted = petService.deletePet(IdPet);

	    if (deleted) {
	        LOGGER.info("Mascota " + IdPet + " eliminada");
	        return ResponseEntity.ok("Mascota eliminada");
	    } else {
	        LOGGER.info("Mascota " + IdPet + " no encontrada");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mascota " + IdPet + " no encontrada");
	    }
	}
	
	
}
