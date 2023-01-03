package jpacrud.blog.dto;

import jpacrud.blog.entity.Board;
import jpacrud.blog.entity.BoardLike;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private int likeCnt;
//    private List<BoardLike> boardLikes = new ArrayList<>();
    private List<CommentResponseDto> comment = new ArrayList<>();

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.memberId = board.getMember().getId();
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }
}
