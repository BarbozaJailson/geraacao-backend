package br.com.belval.api.geraacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InstituicaoAcessoNegadoException extends RuntimeException {
    public InstituicaoAcessoNegadoException(String message) {
        super(message);
    }
}
