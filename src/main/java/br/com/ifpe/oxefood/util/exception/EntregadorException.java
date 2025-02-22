package br.com.ifpe.oxefood.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class EntregadorException extends RuntimeException {
    public static final String MSG_PREFIXO_ENTREGADOR = "Só é permitido Entregadores com um número de pernambuco";
    
    public EntregadorException(String msg) {
	super(String.format(msg));
    }
}