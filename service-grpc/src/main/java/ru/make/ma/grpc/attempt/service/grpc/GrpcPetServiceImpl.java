package ru.make.ma.grpc.attempt.service.grpc;

import io.grpc.Metadata;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.make.ma.grpc.attempt.dto.PetRequestDto;
import ru.make.ma.grpc.attempt.dto.PetResponseDto;
import ru.make.ma.grpc.attempt.server.pet.Main;
import ru.make.ma.grpc.attempt.server.pet.PetServiceGrpc;
import ru.make.ma.grpc.attempt.service.PetService;

import static io.grpc.Status.NOT_FOUND;

@GrpcService
@RequiredArgsConstructor
public class GrpcPetServiceImpl extends PetServiceGrpc.PetServiceImplBase {
    private final PetService petService;

    @Override
    public void createPet(Main.CreatePetRequest request, StreamObserver<Main.CreatePetResponse> responseObserver) {

        PetRequestDto petRequestDTO = PetRequestDto.builder()
                .petName(request.getPet().getPetName())
                .petType(request.getPet().getPetType())
                .petBirthDate(request.getPet().getPetBirthDate())
                .build();

        Main.CreatePetResponse response = Main.CreatePetResponse
                .newBuilder()
                .setPetId(petService.createPet(petRequestDTO))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findByIDPet(Main.FindByIdPetRequest request, StreamObserver<Main.FindByIdPetResponse> responseObserver) {
        PetResponseDto pet = petService.findByIDPet(request.getPetId());

        if (pet == null) {
            Metadata.Key<Main.ErrorResponse> errorResponseKey =
                    ProtoUtils.keyForProto(Main.ErrorResponse.getDefaultInstance());
            Main.ErrorResponse errorResponse = Main.ErrorResponse.newBuilder()
                    .setErrorName("This pet with id = " + request.getPetId() + " is not in the database")
                    .build();
            Metadata metadata = new Metadata();
            metadata.put(errorResponseKey, errorResponse);
            responseObserver.onError(
                    NOT_FOUND.withDescription("This pet with id = " + request.getPetId() + " is not found")
                            .asRuntimeException(metadata)
            );
            return;
        }
        Main.FindByIdPetResponse response = Main.FindByIdPetResponse
                .newBuilder()
                .setPet(Main.FindByIdPetResponse.Pet
                        .newBuilder()
                        .setPetName(pet.petName())
                        .setPetType(pet.petType())
                        .build())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
