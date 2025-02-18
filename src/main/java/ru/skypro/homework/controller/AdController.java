package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.AdsDTO;
import ru.skypro.homework.dto.ad.CreatedAd;
import ru.skypro.homework.dto.ad.UpdatedAd;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {

    @GetMapping
    public ResponseEntity<AdsDTO> getAllAds() {
        log.info("Fetching all ads");

        List<AdDTO> adList = List.of(
                new AdDTO(UUID.randomUUID(), "Title", 100.0, "Some description", UUID.randomUUID())
        );

        AdsDTO ads = new AdsDTO(adList.size(), adList);
        return ResponseEntity.ok(ads);
    }



    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<AdDTO> createAd(@ModelAttribute CreatedAd ad) {
        log.info("Creating new ad: {}", ad);
        AdDTO newAd = new AdDTO(UUID.randomUUID(), ad.getTitle(), ad.getPrice(), "desc", UUID.randomUUID());
        return ResponseEntity.status(HttpStatus.CREATED).body(newAd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDTO> getAdById(@PathVariable UUID id) {
        log.info("Fetching ad by id={}", id);
        ExtendedAdDTO ad = new ExtendedAdDTO(id, "authorFirstName", "authorLastName", "email", "image.jpg","authorLastName", 100.0,  "Seller");
        return ResponseEntity.ok(ad);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable int id) {
        log.info("Deleting ad with id={}", id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDTO> updateAd(@PathVariable UUID id, @RequestBody UpdatedAd ad) {
        log.info("Updating ad with id={}: {}", id, ad);
        AdDTO updatedAd = new AdDTO(id, ad.getTitle(), ad.getPrice(), "updated_image.jpg", UUID.randomUUID());
        return ResponseEntity.ok(updatedAd);
    }

    @GetMapping("/me")
    public ResponseEntity<List<AdDTO>> getAllAdsMe() {
        log.info("Fetching all ads for current user");
        List<AdDTO> userAds = List.of(new AdDTO(UUID.randomUUID(), "User's Ad", 200.0, "user_image.jpg", UUID.randomUUID()));
        return ResponseEntity.ok(userAds);
    }

    @PatchMapping(path = "/{id}/image", consumes = "multipart/form-data")
    public ResponseEntity<String> updateAdImage(@PathVariable int id, @RequestPart("image") MultipartFile image) {
        log.info("Updating image for ad id={} with file: {}", id, image.getOriginalFilename());
        return ResponseEntity.ok("Image updated successfully");
    }
}
