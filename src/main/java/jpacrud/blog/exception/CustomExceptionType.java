package jpacrud.blog.exception;

import org.springframework.http.HttpStatus;

public enum CustomExceptionType implements BaseExceptionType{

    ALREADY_EXISTS_USERNAME(600, HttpStatus.OK, "이미 존재하는 계정입니다."),
    WRONG_PASSWORD(601, HttpStatus.OK, "비밀번호가 일치하지 않습니다."),
    MEMBER_NOT_FOUND(602, HttpStatus.OK, "계정 정보가 없습니다."),

    BOARD_NOT_FOUND(700, HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."),
    NO_AUTHORITY_UPDATE_BOARD(701, HttpStatus.FORBIDDEN, "게시글을 수정할 권한이 없습니다."),
    NO_AUTHORITY_DELETE_BOARD(702, HttpStatus.FORBIDDEN, "게시글을 삭제할 권한이 없습니다."),

    COMMENT_NOT_FOUND(800, HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."),
    NO_AUTHORITY_UPDATE_COMMENT(801, HttpStatus.FORBIDDEN, "댓글을 수정할 권한이 없습니다."),
    NO_AUTHORITY_DELETE_COMMENT(802, HttpStatus.FORBIDDEN, "댓글을 삭제할 권한이 없습니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    CustomExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

}
