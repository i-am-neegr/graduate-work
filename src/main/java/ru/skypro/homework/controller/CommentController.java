package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreatedOrUpdatedComment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class CommentController {

    @GetMapping("{id}/comments")
    public ResponseEntity<CommentsDTO> getComments(@PathVariable UUID id) {
        log.info("Fetching comments for ad with id={}", id);

        CommentDTO comment = new CommentDTO(UUID.randomUUID(), UUID.randomUUID(), "authorFirstName", "authorImage", LocalDateTime.MAX, "path");
        List<CommentDTO> commentsList = List.of(comment);

        CommentsDTO comments = new CommentsDTO(commentsList.size(), commentsList);

        return ResponseEntity.ok(comments);
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable UUID id, @RequestBody CreatedOrUpdatedComment request) {
        log.info("Adding comment to ad with id={}", id);
        return ResponseEntity.ok(createComment(id, request));
    }

    @PatchMapping("{id}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable UUID id, @PathVariable UUID commentId, @RequestBody CreatedOrUpdatedComment request) {
        log.info("Updating comment with id={} for ad with id={}", commentId, id);
        return ResponseEntity.ok(createComment(commentId, request));
    }

    @DeleteMapping("{id}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int id, @PathVariable int commentId) {
        log.info("Deleting comment with id={} for ad with id={}", commentId, id);
        return ResponseEntity.noContent().build();
    }

    private CommentDTO createComment(UUID id, CreatedOrUpdatedComment request) {
        return new CommentDTO(id, UUID.randomUUID(), request.getText(), "a", LocalDateTime.MAX, "path");
    }
}
