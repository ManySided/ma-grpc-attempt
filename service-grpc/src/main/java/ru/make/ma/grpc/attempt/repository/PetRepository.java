package ru.make.ma.grpc.attempt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.make.ma.grpc.attempt.model.Pet;
import ru.make.ma.grpc.attempt.model.PetType;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByPetType(PetType petType);

    List<Pet> findAllByName(String name);
}
