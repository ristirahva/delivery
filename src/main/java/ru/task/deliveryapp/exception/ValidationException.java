package ru.task.deliveryapp.exception;

/**
 * Базовый валидационный exception. При необходимости его можно расширять конкретными типами вадидации.
 */
public class ValidationException extends ApplicationException{
    public ValidationException(String msg) {
        super(msg);
    }
}
