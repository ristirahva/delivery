package ru.task.deliveryapp.exception;

/**
 * Базовый exception сервиса; возможно, его следует вынести из delivery, чтобы использовать также и в basket,
 * но в условиях задания пусть будет так
 */

public class ApplicationException extends RuntimeException{
    public ApplicationException(String msg) {
        super(msg);
    }
}
