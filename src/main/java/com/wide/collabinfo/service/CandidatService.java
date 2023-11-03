package com.wide.collabinfo.service;

import com.wide.collabinfo.model.Candidat;

import java.util.List;
import java.util.Optional;

public interface CandidatService {

    Optional<Candidat> getCandidat(Long id);

    List<Candidat> getCandidats();

    Candidat saveCandidat(Candidat Candidat);

    void deleteCandidat(Long id);

    Optional<Candidat> getCandidatByNom(String nom);

    List<Candidat> getCandidatByPosteCandidat(String posteCandidat);
}
