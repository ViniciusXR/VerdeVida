package VerdeVida;

import VerdeVida.models.Cliente;
import VerdeVida.repositories.ClienteRepository;
import VerdeVida.services.ClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarCliente() {
        Cliente cliente = new Cliente();
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente created = clienteService.criarCliente(cliente);
        assertEquals(cliente, created);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void testListarClientes() {
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteService.listarClientes();
        assertEquals(clientes, result);
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    public void testObterClientePorId() {
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteService.obterClientePorId(1L);
        assertTrue(result.isPresent());
        assertEquals(cliente, result.get());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    public void testAtualizarCliente() {
        Cliente cliente = new Cliente();
        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setNome("Nome Atualizado");
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente updated = clienteService.atualizarCliente(1L, clienteAtualizado);
        assertEquals(clienteAtualizado.getNome(), updated.getNome());
        verify(clienteRepository, times(1)).findById(1L);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void testDeletarCliente() {
        doNothing().when(clienteRepository).deleteById(1L);
        clienteService.deletarCliente(1L);
        verify(clienteRepository, times(1)).deleteById(1L);
    }
}

