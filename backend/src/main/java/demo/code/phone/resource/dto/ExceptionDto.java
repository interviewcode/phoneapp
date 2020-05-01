package demo.code.phone.resource.dto;

import java.util.Collections;
import java.util.Map;

public class ExceptionDto {

  private String message;
  private Map<String, Object> details = Collections.emptyMap();

  public ExceptionDto(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public Map<String, Object> getDetails() {
    return details;
  }
}