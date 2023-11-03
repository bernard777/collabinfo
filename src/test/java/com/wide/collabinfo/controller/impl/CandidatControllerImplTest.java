package com.wide.collabinfo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wide.collabinfo.dto.CandidatDto;
import com.wide.collabinfo.mapper.CandidatDtoMapper;
import com.wide.collabinfo.model.Candidat;
import com.wide.collabinfo.service.CandidatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CandidatControllerImpl.class)
public class CandidatControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidatService candidatService;

    @MockBean
    private CandidatDtoMapper candidatDtoMapper;


    @Test
    public void when_GetCandidatByIdWithValidId_Expect_ReturnCandidat() throws Exception {
        Long candidatId = 1L;
        Candidat candidat = new Candidat();
        candidat.setIdCandidat(candidatId);

        when(candidatService.getCandidat(candidatId)).thenReturn(Optional.of(candidat));

        mockMvc.perform(get("/candidats/by-id/{id}", candidatId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCandidat").value(candidatId));
    }

    @Test
    public void when_GetCandidatByIdWithInvalidId_Expect_ReturnNotFound() throws Exception {
        Long candidatId = 1L;

        when(candidatService.getCandidat(candidatId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/candidats/by-id/{id}", candidatId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void when_GetCandidatsExist_Expect_ReturnCandidats() throws Exception {
        List<Candidat> candidats = new ArrayList<>();
        candidats.add(new Candidat());
        candidats.add(new Candidat());

        when(candidatService.getCandidats()).thenReturn(candidats);

        mockMvc.perform(get("/candidats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(candidats.size()));
    }

    @Test
    public void when_DeleteCandidatWithValidId_Expect_CandidatShouldBeDeletedAndReturnOk() throws Exception {
        Long candidatId = 1L;

        mockMvc.perform(delete("/candidats/{id}", candidatId))
                .andExpect(status().isOk());

        verify(candidatService, times(1)).deleteCandidat(candidatId);
    }

    @Test
    public void when_CreateCandidatWithValidCandidatDto_Expect_ReturnCreatedCandidat() throws Exception {
        CandidatDto candidatDto = new CandidatDto();
        Candidat candidatToSave = new Candidat();
        Candidat candidatSaved = new Candidat();
        candidatSaved.setIdCandidat(1L);

        when(candidatDtoMapper.mapToCandidat(candidatDto)).thenReturn(candidatToSave);
        when(candidatService.saveCandidat(candidatToSave)).thenReturn(candidatSaved);
        when(candidatDtoMapper.mapToCandidatDto(candidatSaved)).thenReturn(candidatDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/candidats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void when_UpdateCandidatWithValidId_Expect_ShouldReturnUpdatedCandidatAndOk() throws Exception {
        Long candidatId = 1L;
        CandidatDto candidatDto = new CandidatDto();
        Candidat candidatToUpdate = new Candidat();
        candidatToUpdate.setIdCandidat(candidatId);
        Candidat savedCandidat = new Candidat();
        savedCandidat.setIdCandidat(candidatId);

        when(candidatService.getCandidat(candidatId)).thenReturn(Optional.of(candidatToUpdate));
        when(candidatDtoMapper.mapToCandidat(candidatDto)).thenReturn(candidatToUpdate);
        when(candidatService.saveCandidat(candidatToUpdate)).thenReturn(savedCandidat);
        when(candidatDtoMapper.mapToCandidatDto(savedCandidat)).thenReturn(candidatDto);

        mockMvc.perform(put("/candidats/{id}", candidatId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void when_UpdateCandidatWithInvalidId_Expect_ReturnNoContent() throws Exception {
        Long candidatId = 1L;
        CandidatDto candidatDto = new CandidatDto();

        when(candidatService.getCandidat(candidatId)).thenReturn(Optional.empty());

        mockMvc.perform(put("/candidats/{id}", candidatId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(candidatDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void when_GetCandidatByNomWithValidNom_Expect_ReturnCandidat() throws Exception {
        String candidatNom = "Doe";
        Candidat candidat = new Candidat();
        candidat.setNom(candidatNom);

        when(candidatService.getCandidatByNom(candidatNom)).thenReturn(Optional.of(candidat));

        mockMvc.perform(get("/candidats/by-nom/{nom}", candidatNom))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value(candidatNom));
    }

    @Test
    public void when_GetCandidatByNomWithInvalidNom_Expect_ReturnNotFound() throws Exception {
        String candidatNom = "John Doe";

        when(candidatService.getCandidatByNom(candidatNom)).thenReturn(Optional.empty());

        mockMvc.perform(get("/candidats/by-nom/{nom}", candidatNom))
                .andExpect(status().isNotFound());
    }

    @Test
    public void when_GetCandidatByPosteCandidatWithValidPoste_Expect_ReturnCandidats() throws Exception {
        String poste = "Developpeur Front";

        List<Candidat> candidats = new ArrayList<>();
        candidats.add(new Candidat());
        candidats.add(new Candidat());

        when(candidatService.getCandidatByPosteCandidat(poste)).thenReturn(candidats);

        mockMvc.perform(get("/candidats/by-poste/{poste}", poste))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(candidats.size()));
    }

    @Test
    public void when_GetCandidatByPosteCandidatWithInvalidPoste_Expect_ReturnEmptyList() throws Exception {
        String poste = "Introuvable";

        when(candidatService.getCandidatByPosteCandidat(poste)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/candidats/by-poste/{poste}", poste))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}

