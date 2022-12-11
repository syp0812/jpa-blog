package jpacrud.blog.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {

    private Long id;
    private String title;
    private String content;
}
