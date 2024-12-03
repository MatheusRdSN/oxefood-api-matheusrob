package br.com.ifpe.oxefood.util.exception;

public class ClienteException extends RuntimeException {
    public static final String MSG_PREFIXO_CLIENTE = "Só é permitido clientes com contato de pernambuco";
    
    public ClienteException(String msg) {
	super(String.format(msg));
    }
}