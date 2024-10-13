package ma.fpl.tp2.web;

import ma.fpl.tp2.entity.Compte;
import ma.fpl.tp2.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comptes")
public class CompteController {

    @Autowired
    private CompteService compteService;

    @PostMapping
    public ResponseEntity<Compte> creerCompte(@RequestBody Compte compte) {
        Compte nouveauCompte = compteService.creerCompte(compte);
        return ResponseEntity.ok(nouveauCompte);
    }

    @GetMapping("/{id}/solde")
    public ResponseEntity<Double> consulterSolde(@PathVariable Long id) {
        Optional<Compte> compte = compteService.consulterSolde(id);
        return compte.map(value -> ResponseEntity.ok(value.getSolde()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/crediter/{montant}")
    public ResponseEntity<Compte> crediter(@PathVariable Long id, @PathVariable double montant) {
        Compte compte = compteService.crediter(id, montant);
        return ResponseEntity.ok(compte);
    }

    @PostMapping("/{id}/debiter/{montant}")
    public ResponseEntity<Compte> debiter(@PathVariable Long id, @PathVariable double montant) {
        Compte compte = compteService.debiter(id, montant);
        return ResponseEntity.ok(compte);
    }
}