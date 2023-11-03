package com.wide.collabinfo.service.impl;

import com.wide.collabinfo.model.Candidat;
import com.wide.collabinfo.repository.CandidatRepository;
import com.wide.collabinfo.service.CandidatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatServiceImpl implements CandidatService {

    private final CandidatRepository candidatRepository;

    public CandidatServiceImpl(CandidatRepository CandidatRepository) {
        this.candidatRepository = CandidatRepository;
    }

    /**
     * Récupère un Candidat par son ID.
     *
     * @param id L'ID du Candidat à récupérer.
     * @return Un objet Optional contenant le Candidat s'il existe, sinon Optional.empty().
     */
    @Override
    public Optional<Candidat> getCandidat(Long id) {
        return candidatRepository.findById(id);
    }

    /**
     * Récupère la liste de tous les Candidats.
     *
     * @return Une liste de Candidats.
     */
    @Override
    public List<Candidat> getCandidats() {
        return candidatRepository.findAll();
    }

    /**
     * Enregistre un Candidat.
     *
     * @param Candidat Le Candidat à enregistrer.
     * @return Le Candidat enregistré.
     */
    @Override
    public Candidat saveCandidat(Candidat Candidat) {
        return candidatRepository.save(Candidat);
    }

    /**
     * Supprime un Candidat par son ID.
     *
     * @param id L'ID du Candidat à supprimer.
     */
    @Override
    public void deleteCandidat(Long id) {
        candidatRepository.deleteById(id);
    }

    /**
     * Récupère un candidat par son Nom
     *
     * @param nom Nom du candidat à rechercher
     * @return Le Candidat correspondant à ce nom
     */
    @Override
    public Optional<Candidat> getCandidatByNom(String nom) {
        return candidatRepository.findCandidatByNom(nom);
    }

    /**
     * Récupère la liste des candidats par son
     *
     * @param posteCandidat Poste du candidat
     * @return une liste de candidat ayant
     */
    @Override
    public List<Candidat> getCandidatByPosteCandidat(String posteCandidat) {
        return candidatRepository.findCandidatByPosteCandidat(posteCandidat);
    }
}
