package ma.fpl.tp2.service;

import ma.fpl.tp2.entity.Compte;
import ma.fpl.tp2.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;

    public Compte creerCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    public Optional<Compte> consulterSolde(Long id) {
        return compteRepository.findById(id);
    }

    public Compte crediter(Long id, double montant) {
        Compte compte = compteRepository.findById(id).orElseThrow(() -> new RuntimeException("Compte non trouvé"));
        compte.setSolde(compte.getSolde() + montant);
        return compteRepository.save(compte);
    }

    public Compte debiter(Long id, double montant) {
        Compte compte = compteRepository.findById(id).orElseThrow(() -> new RuntimeException("Compte non trouvé"));
        if (compte.getSolde() < montant) {
            throw new RuntimeException("Fonds insuffisants");
        }
        compte.setSolde(compte.getSolde() - montant);
        return compteRepository.save(compte);
    }
}
