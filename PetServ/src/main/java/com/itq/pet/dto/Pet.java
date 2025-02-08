package com.itq.pet.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Pet {
	
	public Pet(String idPet, String idClient, String petName, String petBreed, int petAge, float dogWeight) {
		this.idPet = idPet;
		this.idClient = idClient;
		this.petName = petName;
		this.petBreed = petBreed;
		this.petAge = petAge;
		this.dogWeight = dogWeight;
	}
	
	@Pattern(regexp="M-[0-9]{3}", message = "Intoduce un ID de mascota valido")
	private String idPet;
	@Pattern(regexp="C-[0-9]{3}", message = "Intoduce un ID de cliente valido")
	private String idClient;
	@NotBlank(message="Introduce un nombre de mascota valido")
	private String petName;
	@NotBlank(message="Introduce una raza de mascota valida")
	private String petBreed;
	private int petAge;
	@Digits(integer = 2, fraction = 2, message="El peso puede contener hasta 2 digitos depues del punto decimal")
	private float dogWeight;
	
	/**
	 * @return the idPet
	 */
	public String getIdPet() {
		return idPet;
	}
	/**
	 * @param idPet the idPet to set
	 */
	public void setIdPet(String idPet) {
		this.idPet = idPet;
	}
	/**
	 * @return the idClient
	 */
	public String getIdClient() {
		return idClient;
	}
	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	/**
	 * @return the petName
	 */
	public String getPetName() {
		return petName;
	}
	/**
	 * @param petName the petName to set
	 */
	public void setPetName(String petName) {
		this.petName = petName;
	}
	/**
	 * @return the petBreed
	 */
	public String getPetBreed() {
		return petBreed;
	}
	/**
	 * @param petBreed the petBreed to set
	 */
	public void setPetBreed(String petBreed) {
		this.petBreed = petBreed;
	}
	/**
	 * @return the petAge
	 */
	public int getPetAge() {
		return petAge;
	}
	/**
	 * @param petAge the petAge to set
	 */
	public void setPetAge(int petAge) {
		this.petAge = petAge;
	}
	/**
	 * @return the dogWeight
	 */
	public float getDogWeight() {
		return dogWeight;
	}
	/**
	 * @param dogWeight the dogWeight to set
	 */
	public void setDogWeight(float dogWeight) {
		this.dogWeight = dogWeight;
	}
	
	
}
