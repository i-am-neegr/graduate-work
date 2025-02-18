package ru.skypro.homework.dto.ad;

import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdDTO {
    private UUID id;
    private String title;
    private double price;
    private String description;
    private UUID authorId;
}