package com.wide.collabinfo.mapper;

import com.wide.collabinfo.dto.CandidatDto;
import com.wide.collabinfo.model.Candidat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidatDtoMapper {

    CandidatDto mapToCandidatDto(Candidat candidat);

    Candidat mapToCandidat(CandidatDto candidatDto);
}
