package jpacrud.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpacrud.blog.dto.BoardRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @OrderBy("createdAt desc")
    private List<Comment> comments = new ArrayList<>();

    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public Board(BoardRequestDto requestDto, Member member) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.member = member;
    }

    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
