package VerdeVida;

import VerdeVida.models.FilaEspera;
import VerdeVida.models.Mesa;
import VerdeVida.models.Requisicao;
import VerdeVida.repositories.MesaRepository;
import VerdeVida.repositories.RequisicaoRepository;
import VerdeVida.services.FilaEsperaService;
import VerdeVida.services.RequisicaoService;

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

public class RequisicaoServiceTest {

    @Mock
    private RequisicaoRepository requisicaoRepository;

    @Mock
    private MesaRepository mesaRepository;

    @Mock
    private FilaEsperaService filaEsperaService;

    @InjectMocks
    private RequisicaoService requisicaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarRequisicao() {
        Requisicao requisicao = new Requisicao();
        when(requisicaoRepository.save(requisicao)).thenReturn(requisicao);

        Requisicao created = requisicaoService.criarRequisicao(requisicao);
        assertEquals(requisicao, created);
        verify(requisicaoRepository, times(1)).save(requisicao);
    }

    @Test
    public void testAlocarRequisicao() {
        Requisicao requisicao = new Requisicao();
        requisicao.setQuantidade_pessoas(4);
        Mesa mesa = new Mesa();
        mesa.setCapacidade(4);
        mesa.setEstaCheia(false);
        List<Mesa> mesas = Collections.singletonList(mesa);

        when(mesaRepository.findAll()).thenReturn(mesas);
        when(mesaRepository.save(mesa)).thenReturn(mesa);
        when(requisicaoRepository.save(requisicao)).thenReturn(requisicao);

        requisicaoService.alocarRequisicao(requisicao);
        assertTrue(mesa.isEstaCheia());
        assertEquals(mesa, requisicao.getMesa());
        verify(mesaRepository, times(1)).findAll();
        verify(mesaRepository, times(1)).save(mesa);
        verify(requisicaoRepository, times(1)).save(requisicao);
    }

    @Test
    public void testAlocarRequisicaoNaFilaDeEspera() {
        Requisicao requisicao = new Requisicao();
        requisicao.setQuantidade_pessoas(4);
        FilaEspera filaEspera = new FilaEspera();

        when(mesaRepository.findAll()).thenReturn(Collections.emptyList());
        when(filaEsperaService.listarFilasEspera()).thenReturn(Collections.singletonList(filaEspera));
        when(filaEsperaService.criarFilaEspera(filaEspera)).thenReturn(filaEspera);

        requisicaoService.alocarRequisicao(requisicao);
        assertTrue(filaEspera.getRequisicoes().contains(requisicao));
        verify(mesaRepository, times(1)).findAll();
        verify(filaEsperaService, times(1)).listarFilasEspera();
        verify(filaEsperaService, times(1)).criarFilaEspera(filaEspera);
    }

    @Test
    public void testListarRequisicoes() {
        Requisicao requisicao1 = new Requisicao();
        Requisicao requisicao2 = new Requisicao();
        List<Requisicao> requisicoes = Arrays.asList(requisicao1, requisicao2);

        when(requisicaoRepository.findAll()).thenReturn(requisicoes);

        List<Requisicao> result = requisicaoService.listarRequisicoes();
        assertEquals(requisicoes, result);
        verify(requisicaoRepository, times(1)).findAll();
    }

    @Test
    public void testObterRequisicaoPorId() {
        Requisicao requisicao = new Requisicao();
        when(requisicaoRepository.findById(1L)).thenReturn(Optional.of(requisicao));

        Optional<Requisicao> result = requisicaoService.obterRequisicaoPorId(1L);
        assertTrue(result.isPresent());
        assertEquals(requisicao, result.get());
        verify(requisicaoRepository, times(1)).findById(1L);
    }

    @Test
    public void testAtualizarRequisicao() {
        Requisicao requisicao = new Requisicao();
        Requisicao requisicaoAtualizada = new Requisicao();
        requisicaoAtualizada.setStatus("Atualizada");
        when(requisicaoRepository.findById(1L)).thenReturn(Optional.of(requisicao));
        when(requisicaoRepository.save(requisicao)).thenReturn(requisicao);

        Requisicao updated = requisicaoService.atualizarRequisicao(1L, requisicaoAtualizada);
        assertEquals(requisicaoAtualizada.getStatus(), updated.getStatus());
        verify(requisicaoRepository, times(1)).findById(1L);
        verify(requisicaoRepository, times(1)).save(requisicao);
    }

    @Test
    public void testDeletarRequisicao() {
        doNothing().when(requisicaoRepository).deleteById(1L);
        requisicaoService.deletarRequisicao(1L);
        verify(requisicaoRepository, times(1)).deleteById(1L);
    }
}

