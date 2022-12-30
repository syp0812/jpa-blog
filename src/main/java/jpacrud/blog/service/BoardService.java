package jpacrud.blog.service;

import jpacrud.blog.dto.BoardRequestDto;
import jpacrud.blog.dto.BoardResponseDto;
import jpacrud.blog.entity.Board;
import jpacrud.blog.entity.Member;
import jpacrud.blog.exception.CustomException;
import jpacrud.blog.exception.CustomExceptionType;
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
    public BoardResponseDto saveBoard(BoardRequestDto requestDto, Member member) {
        checkMemberExists(memberRepository, member);
        Board board = boardRepository.save(requestDto.toEntity());
        return new BoardResponseDto(board);
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto, Member member) {
        Board board = checkBoardExists(boardRepository, id);
        checkMemberExists(memberRepository, member);

        board.update(requestDto);
        return new BoardResponseDto(board);
    }

    /**
     * 게시글 삭제
     */
    public void deleteBoard(Long id, Member member) {
        Board board = checkBoardExists(boardRepository, id);
        checkMemberExists(memberRepository, member);

        boardRepository.delete(board);
    }

    /**
     * 전체 게시글 목록 조회
     */
    public Page<BoardResponseDto> getBoards(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return boards.map(e -> new BoardResponseDto(e));
    }

    /**
     * 선택 게시글 조회
     */
    public BoardResponseDto findOne(Long boardId) {
        Board board = checkBoardExists(boardRepository,boardId);
        return new BoardResponseDto(board);
    }
    private Member checkMemberExists(MemberRepository memberRepository, Member member) {
        return memberRepository.findByUsername(member.getUsername()).orElseThrow(
                () -> new CustomException(CustomExceptionType.MEMBER_NOT_FOUND)
        );
    }

    private Board checkBoardExists(BoardRepository boardRepository, Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(CustomExceptionType.BOARD_NOT_FOUND)
        );
    }
}
