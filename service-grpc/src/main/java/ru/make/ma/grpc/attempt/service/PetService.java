package ru.make.ma.grpc.attempt.service;

import ru.make.ma.grpc.attempt.dto.PetRequestDto;
import ru.make.ma.grpc.attempt.dto.PetResponseDto;

import java.util.List;

public interface PetService {
    Long createPet(PetRequestDto pet);

    PetResponseDto findByIDPet(Long id);

    List<PetResponseDto> findAllPet();

    List<PetResponseDto> findAllByNamePet(String Name);

    void deletePet(Long petId);

    PetResponseDto updatePet(PetRequestDto pet);
}
