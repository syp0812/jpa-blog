package jpacrud.blog.controller;

import jpacrud.blog.dto.CommentRequestDto;
import jpacrud.blog.dto.CommentResponseDto;
import jpacrud.blog.dto.ResponseDto;
import jpacrud.blog.security.UserDetailsImpl;
import jpacrud.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/boards/{boardId}/comments")
    public CommentResponseDto saveComment(@PathVariable Long boardId,
                                          @RequestBody @Valid CommentRequestDto requestDto,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("---------------------------------------------------------------------");
        System.out.println(requestDto);
        System.out.println(userDetails.getUsername());
        return commentService.saveComment(boardId, requestDto, userDetails.getMember());
    }

    @PutMapping("/boards/{boardId}/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long boardId, @PathVariable Long commentId,
                                            @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(boardId, commentId, requestDto);
    }

    @DeleteMapping("/boards/{boardId}/comments/{commentId}")
    public ResponseDto deleteComment(@PathVariable Long boardId, @PathVariable Long commentId) {
        commentService.deleteComment(boardId, commentId);
        return new ResponseDto(HttpStatus.OK.value(), "success");
    }
}
