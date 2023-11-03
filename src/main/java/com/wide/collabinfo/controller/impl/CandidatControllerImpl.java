package com.wide.collabinfo.controller.impl;


import com.wide.collabinfo.controller.CandidatController;
import com.wide.collabinfo.dto.CandidatDto;
import com.wide.collabinfo.mapper.CandidatDtoMapper;
import com.wide.collabinfo.model.Candidat;
import com.wide.collabinfo.service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("candidats")
public class CandidatControllerImpl implements CandidatController {

    @Autowired
    private CandidatService candidatService;

    @Autowired
    private CandidatDtoMapper candidatDtoMapper;


    @Override
    @GetMapping("/by-id/{id}")
    public ResponseEntity<Optional<Candidat>> getCandidatById(Long id) {

        Optional<Candidat> candidat = candidatService.getCandidat(id);
        if (candidat.isPresent()) {
            return new ResponseEntity<>(candidat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping
    public List<Candidat> getCandidats() {
        return candidatService.getCandidats();
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteCandidat(Long id) {
        candidatService.deleteCandidat(id);
    }

    @Override
    @PostMapping
    public ResponseEntity<CandidatDto> createCandidat(CandidatDto candidatDto) {
        Candidat candidatToSave = candidatDtoMapper.mapToCandidat(candidatDto);
        Candidat candidatSaved = candidatService.saveCandidat(candidatToSave);
        return new ResponseEntity<>(candidatDtoMapper.mapToCandidatDto(candidatSaved), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CandidatDto> updateCandidat(Long id, CandidatDto candidatDto) {
        Optional<Candidat> retrievedCandidat = candidatService.getCandidat(id);
        if (retrievedCandidat.isPresent()) {
            Candidat candidatToUpdate = candidatDtoMapper.mapToCandidat(candidatDto);
            Candidat savedCandidat = candidatService.saveCandidat(candidatToUpdate);
            return ResponseEntity.ok().body(candidatDtoMapper.mapToCandidatDto(savedCandidat));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    @GetMapping("/by-nom/{nom}")
    public ResponseEntity<Optional<Candidat>> getCandidatByNom(@PathVariable("nom") String nom) {
        Optional<Candidat> candidat = candidatService.getCandidatByNom(nom);
        if (candidat.isPresent()) {
            return new ResponseEntity<>(candidat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping("/by-poste/{poste}")
    public List<Candidat> getCandidatByPosteCandidat(@PathVariable("poste") String poste) {
        return candidatService.getCandidatByPosteCandidat(poste);
    }

}
