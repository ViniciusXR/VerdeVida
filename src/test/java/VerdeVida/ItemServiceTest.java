package VerdeVida;

import VerdeVida.models.Item;
import VerdeVida.repositories.ItemRepository;
import VerdeVida.services.ItemService;

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

public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarItem() {
        Item item = new Item();
        when(itemRepository.save(item)).thenReturn(item);

        Item created = itemService.criarItem(item);
        assertEquals(item, created);
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    public void testListarItens() {
        Item item1 = new Item();
        Item item2 = new Item();
        List<Item> itens = Arrays.asList(item1, item2);

        when(itemRepository.findAll()).thenReturn(itens);

        List<Item> result = itemService.listarItens();
        assertEquals(itens, result);
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    public void testObterItemPorId() {
        Item item = new Item();
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        Optional<Item> result = itemService.obterItemPorId(1L);
        assertTrue(result.isPresent());
        assertEquals(item, result.get());
        verify(itemRepository, times(1)).findById(1L);
    }

    @Test
    public void testAtualizarItem() {
        Item item = new Item();
        Item itemAtualizado = new Item();
        itemAtualizado.setNome("Nome Atualizado");
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(itemRepository.save(item)).thenReturn(item);

        Item updated = itemService.atualizarItem(1L, itemAtualizado);
        assertEquals(itemAtualizado.getNome(), updated.getNome());
        verify(itemRepository, times(1)).findById(1L);
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    public void testDeletarItem() {
        doNothing().when(itemRepository).deleteById(1L);
        itemService.deletarItem(1L);
        verify(itemRepository, times(1)).deleteById(1L);
    }
}

