package VerdeVida.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import VerdeVida.models.Comanda;

public interface ComandaRepository extends JpaRepository<Comanda, Long> {
}
