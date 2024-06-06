package VerdeVida.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Bebida")
public class Bebida extends Item {
    
    
}

