package jpacrud.blog.exception;

import org.springframework.http.HttpStatus;

public enum BlogExceptionType implements BaseExceptionType{

    ALREADY_EXISTS_USERNAME(600, HttpStatus.OK, "이미 존재하는 계정입니다."),
    WRONG_PASSWORD(601, HttpStatus.OK, "비밀번호가 일치하지 않습니다."),
    MEMBER_NOT_FOUND(602, HttpStatus.OK, "계정 정보가 없습니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    BlogExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
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
