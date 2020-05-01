package demo.code.phone.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.code.phone.model.exception.BadRequestException;
import demo.code.phone.resource.dto.ExceptionDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestApiExceptionHandler
    extends ResponseEntityExceptionHandler {

  private ObjectMapper objectMapper;

  RestApiExceptionHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }


  @ExceptionHandler(value = {BadRequestException.class})
  protected ResponseEntity<Object> handleBadReqeuestException(
      RuntimeException ex, WebRequest request) {

    return setErrorResponse(request, HttpStatus.BAD_REQUEST, ex);

  }

  private ResponseEntity<Object> setErrorResponse(WebRequest request,
      HttpStatus status, Exception ex) {

    var dto = new ExceptionDto(ex.getMessage());
    var responseBody = "";
    try {

      responseBody = objectMapper.writeValueAsString(dto);

    } catch (JsonProcessingException e) {
      // This should never happen... never say never...
      responseBody = ex.getMessage();
    }

    return handleExceptionInternal(ex, responseBody,
        new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
}

