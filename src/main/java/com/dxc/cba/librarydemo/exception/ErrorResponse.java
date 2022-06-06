package com.dxc.cba.librarydemo.exception;

import java.util.Date;

/**
 * This class is the main error response class representing
 * response status, error message, and timestamp.
 * Usage of this class is to make sure the exception is conveyed to the user in a clear and concise way
 */
public class ErrorResponse {
    private int statusCode;
    private String message;
    private  Date timeStamp;

    public ErrorResponse(int statusCode, String message, Date timeStamp)
    {
        this.statusCode=statusCode;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public ErrorResponse() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
