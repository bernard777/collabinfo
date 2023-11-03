package com.wide.collabinfo.repository;

import com.wide.collabinfo.model.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {

    Optional<Candidat> findCandidatByNom(String nom);

    List<Candidat> findCandidatByPosteCandidat(String posteCandidat);
}
