package jpacrud.blog.repository;

import jpacrud.blog.entity.Comment;
import jpacrud.blog.entity.CommentLike;
import jpacrud.blog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByCommentAndMember(Comment comment, Member member);
    CommentLike findByCommentIdAndMemberId(Long commentId, Long memberId);
}
