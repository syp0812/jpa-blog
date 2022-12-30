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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/api/boards")
    public ResponseEntity<ResponseDto> saveBoard(@RequestBody @Valid BoardRequestDto requestDto,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.saveBoard(requestDto, userDetails.getMember());
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK, "등록 완료"));
    }

    @PutMapping("/api/boards/{id}")
    public ResponseEntity<ResponseDto>  updateBoard(@PathVariable Long id,
                                   @RequestBody @Valid BoardRequestDto requestDto,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.updateBoard(id, requestDto, userDetails.getMember());
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK, "수정 완료"));
    }

    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<ResponseDto> deleteBoard(@PathVariable Long id,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.deleteBoard(id, userDetails.getMember());
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK, "삭제 완료"));
    }

    @GetMapping("/api/boards")
    public Page<BoardResponseDto> getBoards(Pageable pageable) {
        return boardService.getBoards(pageable);
    }

}
