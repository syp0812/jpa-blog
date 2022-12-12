package jpacrud.blog.dto;

import jpacrud.blog.entity.Board;
import jpacrud.blog.entity.BoardLike;
import jpacrud.blog.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private int like;
//    private List<BoardLike> boardLikes = new ArrayList<>();
    private List<Comment> comment = new ArrayList<>();

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.memberId = board.getMember().getId();
        this.like = board.getBoardLikes().size();
        this.comment = board.getComments();
    }
}
