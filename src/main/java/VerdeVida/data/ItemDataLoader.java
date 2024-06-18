package VerdeVida.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import VerdeVida.models.Item;
import VerdeVida.repositories.ItemRepository;

@Component
public class ItemDataLoader implements CommandLineRunner {

    private final ItemRepository itemRepository;

    public ItemDataLoader(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (itemRepository.count() == 0) {

            Item i1 = new Item();
            i1.setNome("Moqueca de Palmito");
            i1.setPreco(39.99);
            i1.setTipo("Comida");
            itemRepository.save(i1);

            Item i2 = new Item();
            i2.setNome("Falafel Assado");
            i2.setPreco(43.99);
            i2.setTipo("Comida");
            itemRepository.save(i2);

            Item i3 = new Item();
            i3.setNome("Salada Primavera com Macarrão Konjac");
            i3.setPreco(32.99);
            i3.setTipo("Comida");
            itemRepository.save(i3);
            
            Item i4 = new Item();
            i4.setNome("Escondidinho de Inhame");
            i4.setPreco(46.99);
            i4.setTipo("Comida");
            itemRepository.save(i4);

            Item i5 = new Item();
            i5.setNome("Strogonoff de Cogumelos");
            i5.setPreco(54.99);
            i5.setTipo("Comida");
            itemRepository.save(i5);

            Item i6 = new Item();
            i6.setNome("Caçarola de Carne com Legumes");
            i6.setPreco(52.99);
            i6.setTipo("Comida");
            itemRepository.save(i6);

            Item i7 = new Item();
            i7.setNome("Água");
            i7.setPreco(4.00);
            i7.setTipo("Bebida");
            itemRepository.save(i7);

            Item i8 = new Item();
            i8.setNome("Suco");
            i8.setPreco(7.00);
            i8.setTipo("Bebida");
            itemRepository.save(i8);

            Item i9 = new Item();
            i9.setNome("Refrigerante");
            i9.setPreco(6.00);
            i9.setTipo("Bebida");
            itemRepository.save(i9);

            Item i10 = new Item();
            i10.setNome("Cerveja");
            i10.setPreco(10.00);
            i10.setTipo("Bebida");
            itemRepository.save(i10);

            Item i11 = new Item();
            i11.setNome("Taça de Vinho");
            i11.setPreco(15.00);
            i11.setTipo("Bebida");
            itemRepository.save(i11);
        }
    }
}
