package ru.task.deliveryapp.exception;

/**
 * Исключение, когда объект не найден.
 */
public class ObjectNotFoundException extends ApplicationException{
    public ObjectNotFoundException(String msg) {
        super(msg);
    }
}
