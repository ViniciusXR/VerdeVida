package VerdeVida.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import VerdeVida.models.Mesa;
import VerdeVida.repositories.MesaRepository;

@Component
public class MesaDataLoader implements CommandLineRunner {

    private final MesaRepository mesaRepository;

    public MesaDataLoader(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (mesaRepository.count() == 0) {

            Mesa m1 = new Mesa();
            m1.setCapacidade(4);
            m1.setEstaCheia(false);
            mesaRepository.save(m1);

            Mesa m2 = new Mesa();
            m2.setCapacidade(4);
            m2.setEstaCheia(false);
            mesaRepository.save(m2);

            Mesa m3 = new Mesa();
            m3.setCapacidade(4);
            m3.setEstaCheia(false);
            mesaRepository.save(m3);

            Mesa m4 = new Mesa();
            m4.setCapacidade(4);
            m4.setEstaCheia(false);
            mesaRepository.save(m4);

            Mesa m5 = new Mesa();
            m5.setCapacidade(6);
            m5.setEstaCheia(false);
            mesaRepository.save(m5);

            Mesa m6 = new Mesa();
            m6.setCapacidade(6);
            m6.setEstaCheia(false);
            mesaRepository.save(m6);

            Mesa m7 = new Mesa();
            m7.setCapacidade(6);
            m7.setEstaCheia(false);
            mesaRepository.save(m7);

            Mesa m8 = new Mesa();
            m8.setCapacidade(6);
            m8.setEstaCheia(false);
            mesaRepository.save(m8);

            Mesa m9 = new Mesa();
            m9.setCapacidade(8);
            m9.setEstaCheia(false);
            mesaRepository.save(m9);

            Mesa m10 = new Mesa();
            m10.setCapacidade(8);
            m10.setEstaCheia(false);
            mesaRepository.save(m10);
        }
    }
}
