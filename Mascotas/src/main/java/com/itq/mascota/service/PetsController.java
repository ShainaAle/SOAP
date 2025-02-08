package com.itq.mascota.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itq.mascota.dto.Pet;

import org.springframework.validation.annotation.Validated;
import java.util.*;

@RestController
@RequestMapping("/pet")
public class PetsController {

    private static final Map<String, Pet> petDatabase = new HashMap<>();  // Simulando una base de datos en memoria

    // Método GET para obtener una mascota específica por ID
    @GetMapping("/{idPet}")
    public ResponseEntity<Pet> readPet(@PathVariable String idPet) {
        Pet pet = petDatabase.get(idPet);

        if (pet != null) {
            return ResponseEntity.ok(pet); // Retorna la mascota en formato JSON
        } else {
            return null;
        }
    }

    // Método POST para crear una nueva mascota
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        if (petDatabase.containsKey(pet.getIdPet())) {
            return ResponseEntity.ok(pet);
        }

        petDatabase.put(pet.getIdPet(), pet); // Guarda la mascota en la "base de datos" en memoria
        return new ResponseEntity<>(pet, HttpStatus.CREATED); // Respuesta 201 Created
    }

    // Método PUT para actualizar una mascota existente por ID
    @PutMapping("/{idPet}")
    public ResponseEntity<Pet> updatePet(@PathVariable(value = "idPet") String idPet,
                                          @RequestBody Pet pet) {
        Pet existingPet = petDatabase.get(idPet);

        if (existingPet != null) {
            pet.setIdPet(idPet); // Actualiza el ID con el de la URL
            petDatabase.put(idPet, pet); // Guarda la mascota actualizada en la "base de datos"
            return ResponseEntity.ok(pet); // Retorna la mascota actualizada
        } else {
            return null;
        }
    }

    // Método DELETE para eliminar una mascota específica por ID
    @DeleteMapping("/{idPet}")
    public ResponseEntity<Message> deletePet(@PathVariable String idPet) {
        Pet pet = petDatabase.remove(idPet);

        if (pet != null) {
            return ResponseEntity.ok(new Message("Mascota eliminada exitosamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new Message("Mascota no encontrada"));
        }
    }
}

class Message {
    private String mensaje;

    public Message(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

