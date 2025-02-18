package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.UserDTO;

import java.util.UUID;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ProfileController {

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword) {
        if (newPassword == null || newPassword.getNewPassword() == null || newPassword.getCurrentPassword().isBlank()) {
            log.warn("Attempt to set an empty or null password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        log.info("Password updated successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getDetailsAboutMe() {
        UserDTO user = getCurrentUser();
        if (user == null) {
            log.warn("Unauthorized access attempt to user profile");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        log.info("User profile retrieved: {}", user);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateDetails(@RequestBody UpdateUser updateUser) {
        if (updateUser == null) {
            log.warn("Update request contains null user data");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        log.info("User profile updated: {}", updateUser);
        return ResponseEntity.ok(updateUser);
    }

    @PatchMapping(path = "/me/image", consumes = "multipart/form-data")
    public ResponseEntity<Void> updateImage(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            log.warn("Empty or null image upload attempt");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        log.info("User profile image updated, file name: {}", file.getOriginalFilename());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private UserDTO getCurrentUser() {
        // Здесь можно добавить реальную логику получения пользователя
        return new UserDTO(UUID.randomUUID(), "John Doe", "johndoe@example.com", "avatar.png", "+123456789", "a", "aga");
    }
}

