package ru.skypro.homework.dto.ad;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedAd {
    private String title;
    private double price;
    private String description;
    private String image;
}
