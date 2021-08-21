package com.maxmendes.votacaoapi.impl.error;

public class AlreadyVoteException extends Exception {
    public AlreadyVoteException(String message) {
        super(message);
    }
}
