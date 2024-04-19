package ru.task.deliveryapp.exception;

/**
 * Выбрасывается при попытке перехода в запрещённый статус.
 */
public class WrongStateException extends ApplicationException{
    public WrongStateException(String msg) {
        super(msg);
    }
}
