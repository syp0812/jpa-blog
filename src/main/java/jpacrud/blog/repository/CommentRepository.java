package jpacrud.blog.repository;

import jpacrud.blog.entity.Board;
import jpacrud.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment>findByMemberAndBoard(Long userId, Long boardId);
    Optional<Comment> findByBoardId(Long boardId);
}
