package jpacrud.blog.controller;

import jpacrud.blog.dto.CommentRequestDto;
import jpacrud.blog.dto.CommentResponseDto;
import jpacrud.blog.dto.ResponseDto;
import jpacrud.blog.security.UserDetailsImpl;
import jpacrud.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/boards//comments")
    public ResponseEntity<ResponseDto> saveComment(@PathVariable Long boardId,
                                                   @RequestBody @Valid CommentRequestDto requestDto) {
        commentService.saveComment(boardId, requestDto);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK, "등록 완료"));
    }

    @PutMapping("/api/boards//comments/{commentId}")
    public ResponseEntity<ResponseDto> updateComment(@PathVariable Long boardId, @PathVariable Long commentId,
                                            @RequestBody CommentRequestDto requestDto) {
        commentService.updateComment(boardId, commentId, requestDto);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK, "수정 완료"));
    }

    @DeleteMapping("/api/boards//comments/{commentId}")
    public ResponseEntity<ResponseDto> deleteComment(@PathVariable Long boardId, @PathVariable Long commentId) {
        commentService.deleteComment(boardId, commentId);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK, "삭제 완료"));
    }
}
