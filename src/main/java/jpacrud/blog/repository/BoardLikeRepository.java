package jpacrud.blog.repository;

import jpacrud.blog.entity.Board;
import jpacrud.blog.entity.BoardLike;
import jpacrud.blog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    Optional<BoardLike> findByBoardAndMember(Board board, Member member);
    BoardLike findByBoardIdAndMemberId(Long boardId, Long memberId);
}
