package com.wide.collabinfo.repository;

import com.wide.collabinfo.helper.DomainDataInitializeHelper;
import com.wide.collabinfo.model.Candidat;
import com.wide.collabinfo.model.Technologie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CandidatRepositoryTest {

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Candidat testCandidat;

    @BeforeEach
    public void setUp() {
        testCandidat = DomainDataInitializeHelper.initCandidatDataTest();
    }

    @Test
    public void when_SaveCandidat_Expect_ShouldReturnSavedCandidat() {

        Candidat savedCandidat = candidatRepository.save(testCandidat);

        Candidat retrievedCandidat = testEntityManager.find(Candidat.class, savedCandidat.getIdCandidat());

        assertThat(savedCandidat).isEqualTo(retrievedCandidat);
    }

    @Test
    public void when_FindCandidatById_Expect_ShouldReturnCandidat() {

        Candidat savedCandidat = testEntityManager.merge(testCandidat);

        Candidat retrievedCandidat = candidatRepository.findById(savedCandidat.getIdCandidat()).orElse(null);

        assertThat(retrievedCandidat).isEqualTo(savedCandidat);

    }

    @Test
    public void when_UpdateCandidat_Expect_ShouldReturnCandidatWithUpdatedInformations() {

        Candidat savedCandidat = testEntityManager.merge(testCandidat);

        savedCandidat.setTechnologie(Technologie.PHP);
        candidatRepository.save(savedCandidat);

        Candidat updatedCandidat = testEntityManager.find(Candidat.class, savedCandidat.getIdCandidat());

        assertThat(updatedCandidat.getTechnologie()).isEqualTo(Technologie.PHP);

    }

    @Test
    public void when_deleteCandidat_Expect_CandidatShouldBeDeleted() {

        Candidat savedCandidat = testEntityManager.merge(testCandidat);

        candidatRepository.deleteById(savedCandidat.getIdCandidat());

        Candidat deletedCandidat = testEntityManager.find(Candidat.class, savedCandidat.getIdCandidat());

        assertThat(deletedCandidat).isNull();
    }

    @Test
    public void when_FindByNomCandidat_Expect_ShouldReturnCandidat() {
        Candidat savedCandidat = testEntityManager.merge(testCandidat);
        Optional<Candidat> retrievedCandidat = candidatRepository.findCandidatByNom
                (savedCandidat.getNom());
        assertThat(retrievedCandidat).isPresent();
        assertThat(retrievedCandidat.get()).isEqualTo(savedCandidat);
    }

    @Test
    public void when_FindByPosteCandidat_Expect_ShouldReturnListOfCandidats() {
        Candidat savedCandidat = testEntityManager.merge(testCandidat);
        List<Candidat> retrievedCandidats = candidatRepository.findCandidatByPosteCandidat(savedCandidat.getPosteCandidat());
        assertThat(retrievedCandidats).contains(savedCandidat);
    }
}
