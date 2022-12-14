package jpacrud.blog.service;

import jpacrud.blog.entity.Comment;
import jpacrud.blog.entity.CommentLike;
import jpacrud.blog.entity.Member;
import jpacrud.blog.exception.BlogException;
import jpacrud.blog.exception.BlogExceptionType;
import jpacrud.blog.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Transactional
    public void saveCommentLike(Long commentId, Member member) {
        memberRepository.findByUsername(member.getUsername()).orElseThrow(
                () -> new BlogException(BlogExceptionType.MEMBER_NOT_FOUND)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new BlogException(BlogExceptionType.BOARD_NOT_FOUND)
        );
        Optional<CommentLike> like = commentLikeRepository.findByCommentAndMember(comment, member);

        if(like.isPresent()) {
            commentLikeRepository.delete(like.get());
        }
        else {
            CommentLike commentLike = new CommentLike(member, comment.getBoard(), comment);
            commentLikeRepository.save(commentLike);
        }
    }

}
