package jpacrud.blog.controller;

import jpacrud.blog.dto.BoardRequestDto;
import jpacrud.blog.dto.BoardResponseDto;
import jpacrud.blog.dto.ResponseDto;
import jpacrud.blog.entity.Board;
import jpacrud.blog.repository.BoardRepository;
import jpacrud.blog.security.UserDetailsImpl;
import jpacrud.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/boards/new")
    public BoardResponseDto saveBoard(@RequestBody @Valid BoardRequestDto requestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.saveBoard(requestDto, userDetails.getMember());
    }

    @PutMapping("/boards/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id,
                                   @RequestBody @Valid BoardRequestDto requestDto,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.updateBoard(id, requestDto, userDetails.getMember());
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseDto deleteBoard(@PathVariable Long boardId,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.deleteBoard(boardId, userDetails.getMember());
        return new ResponseDto(HttpStatus.OK.value(), "success");
    }

    @GetMapping("/boards")
    public Page<BoardResponseDto> getBoards(Pageable pageable) {
        return boardService.getBoards(pageable);
    }

    @GetMapping("/boards/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id) {
        return boardService.findOne(id);
    }
}
