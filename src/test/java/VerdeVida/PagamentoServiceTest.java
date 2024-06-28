package VerdeVida;

import VerdeVida.models.Credito;
import VerdeVida.models.Debito;
import VerdeVida.models.Dinheiro;
import VerdeVida.models.Pagamento;
import VerdeVida.models.Pix;
import VerdeVida.repositories.PagamentoRepository;
import VerdeVida.services.PagamentoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @InjectMocks
    private PagamentoService pagamentoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Pagamento pagamento1 = new Credito();
        Pagamento pagamento2 = new Debito();
        List<Pagamento> pagamentos = Arrays.asList(pagamento1, pagamento2);

        when(pagamentoRepository.findAll()).thenReturn(pagamentos);

        List<Pagamento> result = pagamentoService.findAll();
        assertEquals(pagamentos, result);
        verify(pagamentoRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Pagamento pagamento = new Credito();
        when(pagamentoRepository.findById(1L)).thenReturn(Optional.of(pagamento));

        Optional<Pagamento> result = pagamentoService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(pagamento, result.get());
        verify(pagamentoRepository, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        Pagamento pagamento = new Debito();
        when(pagamentoRepository.save(pagamento)).thenReturn(pagamento);

        Pagamento saved = pagamentoService.save(pagamento);
        assertEquals(pagamento, saved);
        verify(pagamentoRepository, times(1)).save(pagamento);
    }

    @Test
    public void testSaveCredito() {
        Credito pagamento = new Credito();
        pagamento.setValor(100.0);
        pagamento.setDataPagamento(LocalDate.now());
        when(pagamentoRepository.save(pagamento)).thenReturn(pagamento);

        Pagamento saved = pagamentoService.saveCredito(pagamento);
        assertEquals(96.9, saved.getValor());
        assertEquals(pagamento.getDataPagamento().plusDays(30), pagamento.getDataRecebimento());
        verify(pagamentoRepository, times(1)).save(pagamento);
    }

    @Test
    public void testSaveDebito() {
        Debito pagamento = new Debito();
        pagamento.setValor(100.0);
        pagamento.setDataPagamento(LocalDate.now());
        when(pagamentoRepository.save(pagamento)).thenReturn(pagamento);

        Pagamento saved = pagamentoService.saveDebito(pagamento);
        assertEquals(98.6, saved.getValor());
        assertEquals(pagamento.getDataPagamento().plusDays(14), pagamento.getDataRecebimento());
        verify(pagamentoRepository, times(1)).save(pagamento);
    }

    @Test
    public void testSaveDinheiro() {
        Dinheiro pagamento = new Dinheiro();
        pagamento.setValor(100.0);
        pagamento.setDataPagamento(LocalDate.now());
        when(pagamentoRepository.save(pagamento)).thenReturn(pagamento);

        Pagamento saved = pagamentoService.saveDinheiro(pagamento);
        assertEquals(100.0, saved.getValor());
        assertEquals(pagamento.getDataPagamento(), pagamento.getDataRecebimento());
        verify(pagamentoRepository, times(1)).save(pagamento);
    }

    @Test
    public void testSavePix() {
        Pix pagamento = new Pix();
        pagamento.setValor(100.0);
        pagamento.setDataPagamento(LocalDate.now());
        when(pagamentoRepository.save(pagamento)).thenReturn(pagamento);

        Pagamento saved = pagamentoService.savePix(pagamento);
        assertEquals(98.55, saved.getValor());
        assertEquals(pagamento.getDataPagamento(), pagamento.getDataRecebimento());
        verify(pagamentoRepository, times(1)).save(pagamento);
    }

    @Test
    public void testDeleteById() {
        doNothing().when(pagamentoRepository).deleteById(1L);
        pagamentoService.deleteById(1L);
        verify(pagamentoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testCalcularTotalPagoEmUmDia() {
        Pagamento pagamento1 = new Credito();
        pagamento1.setValor(100.0);
        Pagamento pagamento2 = new Debito();
        pagamento2.setValor(200.0);
        List<Pagamento> pagamentos = Arrays.asList(pagamento1, pagamento2);

        when(pagamentoRepository.findByDataPagamento(LocalDate.now())).thenReturn(pagamentos);

        double total = pagamentoService.calcularTotalPagoEmUmDia(LocalDate.now());
        assertEquals(300.0, total);
        verify(pagamentoRepository, times(1)).findByDataPagamento(LocalDate.now());
    }
}

