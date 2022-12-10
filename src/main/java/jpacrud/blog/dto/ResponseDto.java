package jpacrud.blog.dto;

import lombok.Getter;

@Getter
public class ResponseDto {
    private int data;
    private String msg;

    public ResponseDto(int data, String msg) {
        this.data = data;
        this.msg = msg;
    }
}
