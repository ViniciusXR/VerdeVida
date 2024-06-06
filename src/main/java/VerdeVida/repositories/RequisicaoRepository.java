package VerdeVida.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import VerdeVida.models.Requisicao;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {
    
}
