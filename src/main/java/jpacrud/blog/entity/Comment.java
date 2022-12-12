package jpacrud.blog.entity;

import jpacrud.blog.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Comment(CommentRequestDto requestDto, Board board, Member member) {
        this.member = member;
        this.board = board;
        this.content = requestDto.getContent();
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}
