package com.wide.collabinfo.mapper;

import com.wide.collabinfo.dto.CandidatDto;
import com.wide.collabinfo.model.Candidat;
import com.wide.collabinfo.model.Technologie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CandidatDtoMapperTest {

    private CandidatDtoMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(CandidatDtoMapper.class);
    }

    @Test
    public void when_MapCandidatToCandidatDto_Expect_CorrectMapping() {
        // Given
        Candidat candidat = new Candidat();
        candidat.setIdCandidat(1L);
        candidat.setNom("Doe");
        candidat.setPrenom("John");
        candidat.setEmail("john.doe@example.com");
        candidat.setTechnologie(Technologie.JAVA);
        candidat.setDateEnregistrement(LocalDate.of(2023, 9, 27));

        // When
        CandidatDto candidatDto = mapper.mapToCandidatDto(candidat);

        // Then
        assertEquals(1L, candidatDto.getIdCandidat());
        assertEquals("Doe", candidatDto.getNom());
        assertEquals("John", candidatDto.getPrenom());
        assertEquals("john.doe@example.com", candidatDto.getEmail());
        assertEquals(Technologie.JAVA, candidatDto.getTechnologie());
        assertEquals(LocalDate.of(2023, 9, 27), candidatDto.getDateEnregistrement());
    }

    @Test
    public void when_MapCandidatDtoToCandidat_Expect_CorrectMapping() {
        // Given
        CandidatDto candidatDto = new CandidatDto();
        candidatDto.setIdCandidat(2L);
        candidatDto.setNom("Smith");
        candidatDto.setPrenom("Jane");
        candidatDto.setEmail("jane.smith@example.com");
        candidatDto.setTechnologie(Technologie.PYTHON);
        candidatDto.setDateEnregistrement(LocalDate.of(2023, 9, 28));

        // When
        Candidat candidat = mapper.mapToCandidat(candidatDto);

        // Then
        assertEquals(2L, candidat.getIdCandidat());
        assertEquals("Smith", candidat.getNom());
        assertEquals("Jane", candidat.getPrenom());
        assertEquals("jane.smith@example.com", candidat.getEmail());
        assertEquals(Technologie.PYTHON, candidat.getTechnologie());
        assertEquals(LocalDate.of(2023, 9, 28), candidat.getDateEnregistrement());
    }
}

