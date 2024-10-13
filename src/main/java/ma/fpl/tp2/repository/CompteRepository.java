package ma.fpl.tp2.repository;


import ma.fpl.tp2.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {

}