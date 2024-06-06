package VerdeVida.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Comida")
public class Comida extends Item {

}

