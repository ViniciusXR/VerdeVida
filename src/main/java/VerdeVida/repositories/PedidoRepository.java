package VerdeVida.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import VerdeVida.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}

