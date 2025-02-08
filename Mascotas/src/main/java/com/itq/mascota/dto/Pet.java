package com.itq.mascota.dto;



public class Pet {

   private String idPet;
   private String petName;
   private int petAge;
   private float dogWeight;

    // Getters y setters
    public String getIdPet() {
        return idPet;
    }

    public void setIdPet(String idPet) {
        this.idPet = idPet;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetAge() {
        return petAge;
    }

    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }

    public float getDogWeight() {
        return dogWeight;
    }

    public void setDogWeight(float dogWeight) {
        this.dogWeight = dogWeight;
    }
}
