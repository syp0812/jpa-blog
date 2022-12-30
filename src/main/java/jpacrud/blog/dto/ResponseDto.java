package jpacrud.blog.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseDto {
    private HttpStatus code;
    private String msg;

    public ResponseDto(HttpStatus code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
