package ru.skypro.homework.dto.comment;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsDTO {
    private int count;
    private List<CommentDTO> results;
}

