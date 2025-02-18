package ru.skypro.homework.dto.ad;

import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedAdDTO {
    private UUID pk;
    private String authorFirstName;
    private String authorLastName;
    private String email;
    private String image;
    private String phone;
    private double price;
    private String title;
}