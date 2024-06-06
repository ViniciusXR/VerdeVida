package VerdeVida.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import VerdeVida.models.Bebida;

public interface ComidaRepository extends JpaRepository<Bebida, Long> {
}
