package com.wide.collabinfo.controller;

import com.wide.collabinfo.dto.CandidatDto;
import com.wide.collabinfo.model.Candidat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface CandidatController {

    ResponseEntity<Optional<Candidat>> getCandidatById(@PathVariable("id") Long id);

    List<Candidat> getCandidats();

    void deleteCandidat(@PathVariable("id") Long id);

    ResponseEntity<CandidatDto> createCandidat(@RequestBody CandidatDto candidatDto);

    ResponseEntity<CandidatDto> updateCandidat(@PathVariable("id") Long id, @RequestBody CandidatDto candidatDto);

    List<Candidat> getCandidatByPosteCandidat(@PathVariable("poste") String poste);

    ResponseEntity<Optional<Candidat>> getCandidatByNom(@PathVariable("nom") String nom);
}
