package VerdeVida.services;

import VerdeVida.models.Mesa;
import VerdeVida.models.Pedido;
import VerdeVida.models.Requisicao;
import VerdeVida.repositories.MesaRepository;
import VerdeVida.repositories.RequisicaoRepository;
import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    public Mesa criarMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> obterMesaPorId(Long id) {
        return mesaRepository.findById(id);
    }

    public Mesa atualizarMesa(Long id, Mesa mesaAtualizada) {
        return mesaRepository.findById(id)
                .map(mesa -> {
                    mesa.setCapacidade(mesaAtualizada.getCapacidade());
                    mesa.setEstaCheia(mesaAtualizada.isEstaCheia());
                    return mesaRepository.save(mesa);
                })
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
    }

    public void deletarMesa(Long id) {
        mesaRepository.deleteById(id);
    }

    @Transactional
    public Mesa adicionarPedido(Long mesaId, int quantidade, List<Integer> itensIds) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setQuantidade(quantidade);
        pedido.setMesa(mesa); // Define a relação

        List<Long> itensIdsLong = itensIds.stream().map(Long::valueOf).collect(Collectors.toList());

        pedido = pedidoService.criarPedido(pedido, itensIdsLong);

        // Adiciona o pedido à lista de pedidos da mesa
        mesa.getPedidos().add(pedido);

        // Salva a mesa com o novo pedido
        return mesaRepository.save(mesa);
    }

    @Transactional
    public double encerrarPedidos(Long mesaId) {
        Logger logger = LoggerFactory.getLogger(MesaService.class);

        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

        double total = mesa.getPedidos().stream()
                .mapToDouble(Pedido::calcularTotalComTaxa)
                .sum();

        // Arredondar o total para duas casas decimais
        BigDecimal totalRounded = BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP);

        mesa.setEstaCheia(false);

        // Log antes de limpar associações
        logger.info("Estado da mesa antes de limpar associações: {}", mesa);

        // Limpar associações
        for (Pedido pedido : mesa.getPedidos()) {
            pedido.setMesa(null); // Remove a associação de cada pedido com a mesa
        }
        mesa.getPedidos().clear(); // Limpa a lista de pedidos da mesa

        // Limpar associação da requisição
        if (mesa.getRequisicao() != null) {
            Requisicao requisicao = mesa.getRequisicao();
            requisicao.setHora_saida(LocalDateTime.now());
            requisicao.setStatus("Encerrada");
            requisicao.setMesa(null); // Remove a associação da mesa na requisição
            mesa.setRequisicao(null); // Remove a requisição da mesa
            requisicaoRepository.save(requisicao); // Salva a alteração na requisição
        }

        // Log após limpar associações mas antes de salvar
        logger.info("Estado da mesa após limpar associações: {}", mesa);

        // Salva as alterações na mesa
        mesaRepository.save(mesa);

        // Log após salvar
        logger.info("Estado da mesa após salvar: {}", mesa);

        return totalRounded.doubleValue();
    }

    public double dividirConta(Long mesaId) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
        double total = encerrarPedidos(mesaId);
        return total / mesa.getCapacidade();
    }
}
