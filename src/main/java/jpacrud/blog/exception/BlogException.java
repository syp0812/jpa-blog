package jpacrud.blog.exception;

public class BlogException extends BaseException{
    private BaseExceptionType exceptionType;

    public BlogException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
