package jpacrud.blog.dto;

import jpacrud.blog.entity.Board;
import jpacrud.blog.entity.Member;
import lombok.Getter;

import java.util.List;

@Getter
public class CommentRequestDto {
    private String content;
}
