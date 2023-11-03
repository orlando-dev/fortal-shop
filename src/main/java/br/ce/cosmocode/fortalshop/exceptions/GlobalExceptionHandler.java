package br.ce.cosmocode.fortalshop.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.ce.cosmocode.fortalshop.common.ErrorResponse;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError fieldError = result.getFieldError();

        if (fieldError != null) {
            return new ErrorResponse(fieldError.getDefaultMessage());
        } else {
            return new ErrorResponse("Ocorreu um erro de validação");
        }
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return new ErrorResponse("ID de usuário inválido");
    }

    @ExceptionHandler(UserIdNotProvidedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleUserIdNotProvidedException(UserIdNotProvidedException ex) {
        return new ErrorResponse(ex.getMessage());
    }
}
