package VerdeVida.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import VerdeVida.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}

