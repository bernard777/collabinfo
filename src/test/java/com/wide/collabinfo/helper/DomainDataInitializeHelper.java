package com.wide.collabinfo.helper;

import com.wide.collabinfo.model.Candidat;
import com.wide.collabinfo.model.Technologie;

import java.time.LocalDate;

public class DomainDataInitializeHelper {

    public static Candidat initCandidatDataTest() {

        return Candidat.builder()
                .idCandidat(1L)
                .nom("DOE")
                .prenom("John")
                .email("johndoe@gmail.com")
                .telephone("0755339900")
                .adressePostale("15 Rue avenue du soleil")
                .posteCandidat("Developpeur Front")
                .technologie(Technologie.ANGULAR)
                .dateEnregistrement(LocalDate.now())
                .build();
    }
}
