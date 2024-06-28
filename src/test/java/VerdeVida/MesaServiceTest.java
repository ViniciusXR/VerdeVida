package VerdeVida;

import VerdeVida.models.Mesa;
import VerdeVida.models.Pedido;
import VerdeVida.repositories.MesaRepository;
import VerdeVida.repositories.RequisicaoRepository;
import VerdeVida.services.MesaService;
import VerdeVida.services.PedidoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MesaServiceTest {

    @Mock
    private MesaRepository mesaRepository;

    @Mock
    private PedidoService pedidoService;

    @Mock
    private RequisicaoRepository requisicaoRepository;

    @InjectMocks
    private MesaService mesaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarMesa() {
        Mesa mesa = new Mesa();
        when(mesaRepository.save(mesa)).thenReturn(mesa);

        Mesa created = mesaService.criarMesa(mesa);
        assertEquals(mesa, created);
        verify(mesaRepository, times(1)).save(mesa);
    }

    @Test
    public void testListarMesas() {
        Mesa mesa1 = new Mesa();
        Mesa mesa2 = new Mesa();
        List<Mesa> mesas = Arrays.asList(mesa1, mesa2);

        when(mesaRepository.findAll()).thenReturn(mesas);

        List<Mesa> result = mesaService.listarMesas();
        assertEquals(mesas, result);
        verify(mesaRepository, times(1)).findAll();
    }

    @Test
    public void testObterMesaPorId() {
        Mesa mesa = new Mesa();
        when(mesaRepository.findById(1L)).thenReturn(Optional.of(mesa));

        Optional<Mesa> result = mesaService.obterMesaPorId(1L);
        assertTrue(result.isPresent());
        assertEquals(mesa, result.get());
        verify(mesaRepository, times(1)).findById(1L);
    }

    @Test
    public void testAtualizarMesa() {
        Mesa mesa = new Mesa();
        Mesa mesaAtualizada = new Mesa();
        mesaAtualizada.setCapacidade(4);
        when(mesaRepository.findById(1L)).thenReturn(Optional.of(mesa));
        when(mesaRepository.save(mesa)).thenReturn(mesa);

        Mesa updated = mesaService.atualizarMesa(1L, mesaAtualizada);
        assertEquals(mesaAtualizada.getCapacidade(), updated.getCapacidade());
        verify(mesaRepository, times(1)).findById(1L);
        verify(mesaRepository, times(1)).save(mesa);
    }

    @Test
    public void testDeletarMesa() {
        doNothing().when(mesaRepository).deleteById(1L);
        mesaService.deletarMesa(1L);
        verify(mesaRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testAdicionarPedido() {
        Mesa mesa = new Mesa();
        when(mesaRepository.findById(1L)).thenReturn(Optional.of(mesa));
        Pedido pedido = new Pedido();
        when(pedidoService.criarPedido(any(Pedido.class), anyList())).thenReturn(pedido);
        when(mesaRepository.save(mesa)).thenReturn(mesa);

        Mesa result = mesaService.adicionarPedido(1L, 2, Collections.singletonList(1));
        assertTrue(result.getPedidos().contains(pedido));
        verify(mesaRepository, times(1)).findById(1L);
        verify(pedidoService, times(1)).criarPedido(any(Pedido.class), anyList());
        verify(mesaRepository, times(1)).save(mesa);
    }

    @Test
    public void testEncerrarPedidos() {
        Mesa mesa = new Mesa();
        Pedido pedido = new Pedido();
        pedido.setMesa(mesa);
        pedido.setQuantidade(2);
        mesa.setPedidos(Collections.singletonList(pedido));
        when(mesaRepository.findById(1L)).thenReturn(Optional.of(mesa));
        when(mesaRepository.save(mesa)).thenReturn(mesa);

        double total = mesaService.encerrarPedidos(1L);
        assertEquals(0, mesa.getPedidos().size());
        verify(mesaRepository, times(1)).findById(1L);
        verify(mesaRepository, times(1)).save(mesa);
    }
}

