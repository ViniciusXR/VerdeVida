package VerdeVida.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import VerdeVida.models.Cardapio;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
}

