package com.leapxpert.usermanagement.service.exception;

public class ServiceException extends RuntimeException {

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(String format, Object... objects) {
    super(String.format(format, objects));
  }
}