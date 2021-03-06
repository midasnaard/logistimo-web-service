package com.logistimo.exception;

/**
 * Created by charan on 23/06/17.
 */
public class HttpBadRequestException extends LogiException {

  private final ErrorResponse response;

  public HttpBadRequestException(ErrorResponse response, Throwable exception) {
    super(response != null ? response.getCode() : "G001", exception, new Object[0]);
    this.setStatusCode(response != null ? response.getStatusCode() : 500);
    this.response = response;
  }

  public ErrorResponse getResponse() {
    return response;
  }

  public String getMessage() {
    return response.getMessage();
  }
}
