package GestionBudget.budget.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ApplicationControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody MessageErreur handlerException(NotFoundException exception){
        return new MessageErreur(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidException.class)
    public @ResponseBody MessageErreur handlerException(InvalidException exception){
        return new MessageErreur((HttpStatus.BAD_REQUEST.value()), exception.getMessage());
    }
    @ResponseStatus(HttpStatus.MULTI_STATUS)
    @ExceptionHandler(DuplicateException.class)
    public @ResponseBody MessageErreur handlerException(DuplicateException exception){
        return new MessageErreur(HttpStatus.MULTI_STATUS.value(), exception.getMessage());
    }
}
