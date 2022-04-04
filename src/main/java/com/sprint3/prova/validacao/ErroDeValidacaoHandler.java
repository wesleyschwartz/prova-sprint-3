package com.sprint3.prova.validacao;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormularioDTO> handle(MethodArgumentNotValidException exception) {
        List<ErroDeFormularioDTO> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> {
                    String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                    ErroDeFormularioDTO erro = new ErroDeFormularioDTO(fieldError.getField(), mensagem);
                    dto.add(erro);
                }
        );
        return dto;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConversionFailedException.class)
    public ErroDeFormularioDTO handle(ConversionFailedException exception) {

        return new ErroDeFormularioDTO(("Parâmetro(s) Inválido(s): " + exception.getValue()), (exception.getMostSpecificCause().getMessage()));
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public ErroDeFormularioDTO handle(InvalidFormatException exception) {

        return new ErroDeFormularioDTO(("Parâmetro(s) Inválido(s): " + exception.getValue()), exception.getOriginalMessage());
    }
}
