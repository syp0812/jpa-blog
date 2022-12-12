package jpacrud.blog.controller;

import jpacrud.blog.dto.ResponseDto;
import jpacrud.blog.security.UserDetailsImpl;
import jpacrud.blog.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    @PostMapping("/comments/{commentId}/like")
    public ResponseDto saveCommentLike(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentLikeService.saveCommentLike(commentId, userDetails.getMember());
        return new ResponseDto(HttpStatus.OK.value(), "success");
    }
}
