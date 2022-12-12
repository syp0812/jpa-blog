package jpacrud.blog.service;

import jpacrud.blog.entity.Board;
import jpacrud.blog.entity.BoardLike;
import jpacrud.blog.entity.Member;
import jpacrud.blog.exception.BlogException;
import jpacrud.blog.exception.BlogExceptionType;
import jpacrud.blog.repository.BoardRepository;
import jpacrud.blog.repository.BoardLikeRepository;
import jpacrud.blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardLikeService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;

    @Transactional
    public void saveLike(Long boardId, Member member) {
        memberRepository.findByUsername(member.getUsername()).orElseThrow(
                () -> new BlogException(BlogExceptionType.MEMBER_NOT_FOUND)
        );
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new BlogException(BlogExceptionType.BOARD_NOT_FOUND)
        );
        Optional<BoardLike> like = boardLikeRepository.findByBoardAndMember(board, member);

        // 이미 ‘좋아요’한 게시글에 다시 요청을 하면 ‘좋아요’를 했던 기록이 취소
        if(like.isPresent()) {
            BoardLike boardLike = boardLikeRepository.findByBoardIdAndMemberId(boardId, member.getId());
            boardLikeRepository.delete(boardLike);
        }
        else {
            BoardLike boardLike = new BoardLike(member, board);
            boardLikeRepository.save(boardLike);
        }
    }
}
