package jpacrud.blog.dto;

import jpacrud.blog.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long id;
    private Long boardId;
    private Long memberId;
    private String content;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.boardId = comment.getBoard().getId();
        this.memberId = comment.getMember().getId();
        this.content = comment.getContent();
    }
}
