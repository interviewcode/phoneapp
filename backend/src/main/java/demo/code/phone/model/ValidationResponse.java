package demo.code.phone.model;

public class ValidationResponse {

  private boolean valid;

  public ValidationResponse(boolean valid) {
    this.valid = valid;
  }

  public boolean isValid() {
    return valid;
  }
}
