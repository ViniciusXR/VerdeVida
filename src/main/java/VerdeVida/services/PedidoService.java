package VerdeVida.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import VerdeVida.models.Item;
import VerdeVida.models.Pedido;
import VerdeVida.repositories.ItemRepository;
import VerdeVida.repositories.PedidoRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public Pedido criarPedido(Pedido pedido, List<Long> itensIds) {
        List<Item> itens = itemRepository.findAllById(itensIds);
        pedido.setItens(itens);
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
                    pedido.setItens(pedidoAtualizado.getItens());
                    pedido.setQuantidade(pedidoAtualizado.getQuantidade());
                    return pedidoRepository.save(pedido);
                })
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
