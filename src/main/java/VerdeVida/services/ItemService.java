package VerdeVida.services;

import VerdeVida.models.Item;
import VerdeVida.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item criarItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> listarItens() {
        return itemRepository.findAll();
    }

    public Optional<Item> obterItemPorId(Long id) {
        return itemRepository.findById(id);
    }

    public Item atualizarItem(Long id, Item itemAtualizado) {
        return itemRepository.findById(id)
                .map(item -> {
                    item.setNome(itemAtualizado.getNome());
                    item.setPreco(itemAtualizado.getPreco());
                    item.setTipo(itemAtualizado.getTipo());
                    return itemRepository.save(item);
                })
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

    public void deletarItem(Long id) {
        itemRepository.deleteById(id);
    }
}
