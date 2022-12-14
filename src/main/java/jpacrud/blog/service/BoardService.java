package jpacrud.blog.service;

import jpacrud.blog.dto.BoardRequestDto;
import jpacrud.blog.dto.BoardResponseDto;
import jpacrud.blog.entity.Board;
import jpacrud.blog.entity.Member;
import jpacrud.blog.exception.BlogException;
import jpacrud.blog.exception.BlogExceptionType;
import jpacrud.blog.repository.BoardRepository;
import jpacrud.blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /*
     * 게시글 작성
     */
    @Transactional
    public BoardResponseDto saveBoard(BoardRequestDto requestDto, Member member) {
        Board board = new Board(requestDto, member);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto requestDto, Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(() ->
                new BlogException(BlogExceptionType.BOARD_NOT_FOUND));

        memberRepository.findByUsername(member.getUsername()).orElseThrow(
                () -> new BlogException(BlogExceptionType.NO_AUTHORITY_UPDATE_BOARD)
        );

        board.update(requestDto);
        return new BoardResponseDto(board);
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deleteBoard(Long boardId, Member member) {
        Board board = checkBoard(boardRepository,boardId);

        memberRepository.findByUsername(member.getUsername()).orElseThrow(
                () -> new BlogException(BlogExceptionType.NO_AUTHORITY_UPDATE_BOARD)
        );
        boardRepository.delete(board);
    }

    /**
     * 전체 게시글 목록 조회
     */
    @Transactional(readOnly = true)
    public Page<BoardResponseDto> getBoards(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return boards.map(e -> new BoardResponseDto(e));
    }

    /**
     * 선택 게시글 조회
     */
    @Transactional(readOnly = true)
    public BoardResponseDto findOne(Long boardId) {
        Board board = checkBoard(boardRepository,boardId);
        return new BoardResponseDto(board);
    }

    private Board checkBoard(BoardRepository boardRepository, Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                () -> new BlogException(BlogExceptionType.BOARD_NOT_FOUND)
        );
    }
}
