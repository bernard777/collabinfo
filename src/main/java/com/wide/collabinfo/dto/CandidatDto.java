package com.wide.collabinfo.dto;

import com.wide.collabinfo.model.Technologie;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CandidatDto {

    private Long idCandidat;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adressePostale;
    private String posteCandidat;
    private Technologie technologie;
    private LocalDate dateEnregistrement;
}
