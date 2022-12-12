package jpacrud.blog.controller;

import jpacrud.blog.dto.ResponseDto;
import jpacrud.blog.security.UserDetailsImpl;
import jpacrud.blog.service.BoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardLikeController {

    private final BoardLikeService boardLikeService;

    @PostMapping("/boards/{boardId}/like")
    public ResponseDto saveLike(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl details) {
        boardLikeService.saveLike(boardId, details.getMember());
        return new ResponseDto(HttpStatus.OK.value(), "success");
    }
}
