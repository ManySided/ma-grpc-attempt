package ru.make.ma.grpc.attempt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.make.ma.grpc.attempt.model.PetType;

@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Long> {
    PetType findByName(String name);
}
