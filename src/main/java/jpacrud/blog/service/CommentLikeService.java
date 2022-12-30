package jpacrud.blog.service;

import jpacrud.blog.entity.Comment;
import jpacrud.blog.entity.CommentLike;
import jpacrud.blog.entity.Member;
import jpacrud.blog.exception.CustomException;
import jpacrud.blog.exception.CustomExceptionType;
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
                () -> new CustomException(CustomExceptionType.MEMBER_NOT_FOUND)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomException(CustomExceptionType.BOARD_NOT_FOUND)
        );
        Optional<CommentLike> like = commentLikeRepository.findByCommentAndMember(comment, member);

        if(like.isPresent()) {
            commentLikeRepository.delete(like.get());
        }
        else {
            CommentLike commentLike = new CommentLike(member, comment);
            commentLikeRepository.save(commentLike);
        }
    }

}
