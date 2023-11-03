package com.wide.collabinfo.service;

import com.wide.collabinfo.helper.DomainDataInitializeHelper;
import com.wide.collabinfo.model.Candidat;
import com.wide.collabinfo.repository.CandidatRepository;
import com.wide.collabinfo.service.impl.CandidatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CandidatServiceImplTest {

    private final Long NON_EXISTING_ID = 999L;

    @Mock
    private CandidatRepository candidatRepository;

    @InjectMocks
    private CandidatServiceImpl candidatService;

    private Candidat candidat;

    private List<Candidat> candidatList;

    @BeforeEach
    public void setUp() {
        candidat = DomainDataInitializeHelper.initCandidatDataTest();
        candidatList = new ArrayList<>();
        candidatList.add(DomainDataInitializeHelper.initCandidatDataTest());
    }

    @Test
    public void when_GetCandidat_Expect_ShouldReturnCandidat() {

        Long idCandidat = candidat.getIdCandidat();

        when(candidatRepository.findById(idCandidat)).thenReturn(Optional.of(candidat));

        Optional<Candidat> result = candidatService.getCandidat(idCandidat);

        assertTrue(result.isPresent());

    }

    @Test
    public void when_GetCandidatWithNonExistingId_Expect_shouldReturnOptionalEmpty() {

        when(candidatRepository.findById(NON_EXISTING_ID)).thenReturn(Optional.empty());

        Optional<Candidat> result = candidatService.getCandidat(NON_EXISTING_ID);

        assertTrue(result.isEmpty());

        verify(candidatRepository, times(1)).findById(NON_EXISTING_ID);

    }

    @Test
    public void when_GetCandidats_Expect_ShouldReturnListOfCandidats() {

        when(candidatRepository.findAll()).thenReturn(candidatList);

        List<Candidat> result = candidatService.getCandidats();

        assertEquals(result.size(), candidatList.size());

    }

    @Test
    public void when_SaveCandidat_Expect_ShouldReturnSavedCandidat() {

        Candidat candidatToSave = DomainDataInitializeHelper.initCandidatDataTest();

        when(candidatRepository.save(candidatToSave)).thenReturn(candidat);

        Candidat candidatSaved = candidatService.saveCandidat(candidatToSave);

        assertEquals(candidatSaved.getIdCandidat(), candidat.getIdCandidat());


    }

    @Test
    public void when_DeleteCandidat_Expect_CandidatShouldBeDeleted() {

        Long idCandidat = 1L;

        candidatService.deleteCandidat(idCandidat);

        verify(candidatRepository, times(1)).deleteById(idCandidat);

    }

    @Test
    public void when_DeleteCandidatWithNonExistingId_Expect_CandidatShouldNotBeDeleted() {

        candidatService.deleteCandidat(NON_EXISTING_ID);

        verify(candidatRepository, times(1)).deleteById(NON_EXISTING_ID);
    }

    @Test
    public void when_GetCandidatByNom_Expect_ShouldReturnCandidat() {
        String nom = "Doe";
        when(candidatRepository.findCandidatByNom(nom)).thenReturn(Optional.of(candidat));

        Optional<Candidat> result = candidatService.getCandidatByNom(nom);

        assertTrue(result.isPresent());
        assertEquals(result.get(), candidat);
    }

    @Test
    public void when_GetCandidatByNomWithNonExistingNom_Expect_ShouldReturnOptionalEmpty() {
        String nom = "Non-Existing Name";
        when(candidatRepository.findCandidatByNom(nom)).thenReturn(Optional.empty());

        Optional<Candidat> result = candidatService.getCandidatByNom(nom);

        assertTrue(result.isEmpty());
        verify(candidatRepository, times(1)).findCandidatByNom(nom);
    }

    @Test
    public void when_GetCandidatByPosteCandidat_Expect_ShouldReturnListOfCandidats() {
        String posteCandidat = "Developpeur Front";
        when(candidatRepository.findCandidatByPosteCandidat(posteCandidat)).thenReturn(candidatList);

        List<Candidat> result = candidatService.getCandidatByPosteCandidat(posteCandidat);

        assertEquals(result.size(), candidatList.size());
        assertEquals(result.get(0), candidatList.get(0));
    }
}

