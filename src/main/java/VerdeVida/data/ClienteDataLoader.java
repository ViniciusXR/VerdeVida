package VerdeVida.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import VerdeVida.models.Cliente;
import VerdeVida.repositories.ClienteRepository;

@Component
public class ClienteDataLoader implements CommandLineRunner {

    private final ClienteRepository clienteRepository;

    public ClienteDataLoader(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (clienteRepository.count() == 0) {

            Cliente c1 = new Cliente();
            c1.setNome("Ana");
            c1.setTelefone("(31)99999-0001)");
            clienteRepository.save(c1);

            Cliente c2 = new Cliente();
            c2.setNome("Bruno");
            c2.setTelefone("(31)99999-0002)");
            clienteRepository.save(c2);

            Cliente c3 = new Cliente();
            c3.setNome("Carla");
            c3.setTelefone("(31)99999-0003)");
            clienteRepository.save(c3);

            Cliente c4 = new Cliente();
            c4.setNome("Douglas");
            c4.setTelefone("(31)99999-0004)");
            clienteRepository.save(c4);

            Cliente c5 = new Cliente();
            c5.setNome("Emerson");
            c5.setTelefone("(31)99999-0005)");
            clienteRepository.save(c5);

            Cliente c6 = new Cliente();
            c6.setNome("Fernanda");
            c6.setTelefone("(31)99999-0006)");
            clienteRepository.save(c6);

            Cliente c7 = new Cliente();
            c7.setNome("Guilherme");
            c7.setTelefone("(31)99999-0007)");
            clienteRepository.save(c7);

            Cliente c8 = new Cliente();
            c8.setNome("Helio");
            c8.setTelefone("(31)99999-0008)");
            clienteRepository.save(c8);

            Cliente c9 = new Cliente();
            c9.setNome("Isabela");
            c9.setTelefone("(31)99999-0009)");
            clienteRepository.save(c9);

            Cliente c10 = new Cliente();
            c10.setNome("Jose");
            c10.setTelefone("(31)99999-0010)");
            clienteRepository.save(c10);

            Cliente c11 = new Cliente();
            c11.setNome("Kelly");
            c11.setTelefone("(31)99999-0011)");
            clienteRepository.save(c11);
        }
    }

}
