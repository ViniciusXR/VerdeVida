package VerdeVida.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import VerdeVida.models.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}

