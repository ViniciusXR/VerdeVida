package VerdeVida.services;

import VerdeVida.models.Pedido;
import VerdeVida.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> obterPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setItem(pedidoAtualizado.getItem());
                    pedido.setQuantidade(pedidoAtualizado.getQuantidade());
                    pedido.setTipoPagamento(pedidoAtualizado.getTipoPagamento());
                    pedido.setDetalhePagamento(pedidoAtualizado.getDetalhePagamento());
                    pedido.setValorFinal(pedidoAtualizado.getItem().getPreco() * pedidoAtualizado.getQuantidade());
                    return pedidoRepository.save(pedido);
                })
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}

