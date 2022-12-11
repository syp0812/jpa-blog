package jpacrud.blog.service;

import jpacrud.blog.dto.CommentRequestDto;
import jpacrud.blog.dto.CommentResponseDto;
import jpacrud.blog.entity.Board;
import jpacrud.blog.entity.Comment;
import jpacrud.blog.entity.Member;
import jpacrud.blog.exception.BlogException;
import jpacrud.blog.exception.BlogExceptionType;
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

    @Transactional
    public CommentResponseDto saveComment(Long boardId, CommentRequestDto requestDto, Member member) {
        // 게시글 조회
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new BlogException(BlogExceptionType.BOARD_NOT_FOUND)
        );
        Comment comment = new Comment(requestDto, board, member);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long boardId, Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new BlogException(BlogExceptionType.NO_AUTHORITY_UPDATE_COMMENT)
        );
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new BlogException(BlogExceptionType.BOARD_NOT_FOUND)
        );
        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public void deleteComment(Long boardId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new BlogException(BlogExceptionType.NO_AUTHORITY_DELETE_COMMENT)
        );
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new BlogException(BlogExceptionType.BOARD_NOT_FOUND)
        );
        commentRepository.delete(comment);
    }
}
