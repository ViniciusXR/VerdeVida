package VerdeVida.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import VerdeVida.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

