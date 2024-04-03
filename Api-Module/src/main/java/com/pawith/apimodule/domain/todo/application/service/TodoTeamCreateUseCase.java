package com.pawith.apimodule.domain.todo.application.service;

import com.pawith.commonmodule.annotation.ApplicationService;
import com.pawith.imagemodule.service.ImageUploadService;
import com.pawith.apimodule.domain.todo.application.dto.request.TodoTeamCreateRequest;
import com.pawith.apimodule.domain.todo.application.mapper.PetMapper;
import com.pawith.apimodule.domain.todo.application.mapper.TodoTeamMapper;
import com.pawith.tododomain.entity.Pet;
import com.pawith.tododomain.entity.TodoTeam;
import com.pawith.tododomain.service.PetSaveService;
import com.pawith.tododomain.service.RegisterSaveService;
import com.pawith.tododomain.service.TodoTeamSaveService;
import com.pawith.domain.User;
import com.pawith.domain.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CompletableFuture;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class TodoTeamCreateUseCase {

    private final UserUtils userUtils;
    private final TodoTeamSaveService todoTeamSaveService;
    private final RegisterSaveService registerSaveService;
    private final PetSaveService petSaveService;
    private final ImageUploadService imageUploadService;

    public void createTodoTeam(final MultipartFile teamImageFile,final List<MultipartFile> imageFiles,final TodoTeamCreateRequest request) {
        final CompletableFuture<String> teamImageAsync = imageUploadService.uploadImgAsync(teamImageFile);
        final List<CompletableFuture<String>> petImageAsync = imageUploadService.uploadImgListAsync(imageFiles);
        final User user = userUtils.getAccessUser();

        final TodoTeam todoTeam = TodoTeamMapper.mapToTodoTeam(request, teamImageAsync.join());
        todoTeamSaveService.saveTodoTeamEntity(todoTeam);

        registerSaveService.saveRegisterAboutPresident(todoTeam, user.getId());
        final ListIterator<CompletableFuture<String>> futureListIterator = petImageAsync.listIterator();
        request.getPetRegisters().forEach(petRegister -> {
            String imageUrl = futureListIterator.next().join();
            final Pet pet = PetMapper.mapToPet(petRegister, todoTeam, imageUrl);
            petSaveService.savePetEntity(pet);
        });
    }

}
