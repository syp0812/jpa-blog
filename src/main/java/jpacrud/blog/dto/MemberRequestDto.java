package jpacrud.blog.dto;

import lombok.Getter;

@Getter
public class MemberRequestDto {
    private String username;
    private String password;
    private boolean admin = false;
    private String adminToken = "";
}
