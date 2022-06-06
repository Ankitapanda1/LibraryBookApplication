package com.dxc.cba.librarydemo.exception;

/**
 * This class is responsible for handling scenarios and throwing custom exceptions
 * like when an employee is not available in the database or
 *  some validation exception for mandatory fields.
 *  This exception class should extend RuntimeException.
 */
public class CustomLibraryException extends  RuntimeException{
    public CustomLibraryException() {
    }

    public CustomLibraryException(String message) {
        super(message);
    }


}
