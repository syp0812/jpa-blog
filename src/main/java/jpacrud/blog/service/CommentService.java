package jpacrud.blog.service;

import jpacrud.blog.dto.CommentRequestDto;
import jpacrud.blog.dto.CommentResponseDto;
import jpacrud.blog.entity.Board;
import jpacrud.blog.entity.Comment;
import jpacrud.blog.entity.Member;
import jpacrud.blog.exception.CustomException;
import jpacrud.blog.exception.CustomExceptionType;
import jpacrud.blog.repository.BoardRepository;
import jpacrud.blog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public CommentResponseDto saveComment(Long boardId, CommentRequestDto requestDto) {
        // 게시글 조회
        checkBoardExists(boardRepository, boardId);
        Comment comment = commentRepository.save(requestDto.toEntity());
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long boardId, Long commentId, CommentRequestDto requestDto) {
        checkBoardExists(boardRepository, boardId);
        Comment comment = checkCommentExists(commentRepository, commentId);
        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long boardId, Long commentId) {
        checkBoardExists(boardRepository, boardId);
        Comment comment = checkCommentExists(commentRepository, commentId);
        commentRepository.delete(comment);
    }

    private Board checkBoardExists(BoardRepository boardRepository, Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(CustomExceptionType.BOARD_NOT_FOUND)
        );
    }

    private Comment checkCommentExists(CommentRepository commentRepository, Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new CustomException(CustomExceptionType.COMMENT_NOT_FOUND)
        );
    }
}
