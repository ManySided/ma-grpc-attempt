package ru.make.ma.grpc.attempt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.make.ma.grpc.attempt.dto.PetRequestDto;
import ru.make.ma.grpc.attempt.dto.PetResponseDto;
import ru.make.ma.grpc.attempt.model.Pet;
import ru.make.ma.grpc.attempt.repository.PetRepository;
import ru.make.ma.grpc.attempt.repository.PetTypeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    @Override
    public Long createPet(PetRequestDto pet) {
        Pet createPet = Pet
                .builder()
                .name(pet.petName())
                .petType(petTypeRepository.findByName(pet.petType()))
                .birthDate(LocalDate.parse(pet.petBirthDate()))
                .build();

        petRepository.save(createPet);

        return createPet.getId();
    }

    @Override
    public PetResponseDto findByIDPet(Long id) {

        Optional<Pet> pet = petRepository.findById(id);

        return pet.map(value -> PetResponseDto
                .builder()
                .petName(value.getName())
                .petType(petTypeRepository.findById(
                        value.getPetType().getId()).get().getName())

                .build()).orElse(null);

    }

    @Override
    public List<PetResponseDto> findAllPet() {
        List<Pet> pets = petRepository.findAll();
        return addList(pets);
    }

    @Override
    public List<PetResponseDto> findAllByNamePet(String name) {
        List<Pet> pets = petRepository.findAllByName(name);
        return addList(pets);
    }

    @Override
    public void deletePet(Long petId) {

    }

    @Override
    public PetResponseDto updatePet(PetRequestDto pet) {
        return null;
    }

    private List<PetResponseDto> addList(List<Pet> pets) {
        List<PetResponseDto> petResponseDTOList = pets.stream().map(pet -> PetResponseDto
                .builder()
                .petName(pet.getName())
                .petType(pet.getPetType().getName())
                .build()).collect(Collectors.toList());
        return petResponseDTOList;
    }
}
