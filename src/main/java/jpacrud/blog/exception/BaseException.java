package jpacrud.blog.exception;

public abstract class BaseException extends RuntimeException{
    public abstract BaseExceptionType getExceptionType();
}
