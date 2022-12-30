package jpacrud.blog.dto;

import jpacrud.blog.entity.Comment;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
