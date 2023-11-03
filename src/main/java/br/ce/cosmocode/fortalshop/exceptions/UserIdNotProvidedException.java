package br.ce.cosmocode.fortalshop.exceptions;

public class UserIdNotProvidedException extends RuntimeException {
    public UserIdNotProvidedException() {
        super("Forneça um ID de usuário");
    }
}
