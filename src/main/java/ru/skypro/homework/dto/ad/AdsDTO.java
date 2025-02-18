package ru.skypro.homework.dto.ad;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdsDTO {
    private int count;
    private List<AdDTO> results;
}