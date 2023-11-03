package br.ce.cosmocode.fortalshop.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("Nome de usuário já existe");
    }
}
