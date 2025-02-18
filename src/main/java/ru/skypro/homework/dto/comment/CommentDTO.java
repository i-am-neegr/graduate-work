package ru.skypro.homework.dto.comment;

import lombok.*;
import java.util.UUID;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private UUID id;
    private UUID authorId;
    private String authorFirstName;
    private String authorImage;
    private LocalDateTime createdAt;
    private String text;
}