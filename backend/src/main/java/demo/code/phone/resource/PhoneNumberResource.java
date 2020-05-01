package demo.code.phone.resource;

import demo.code.phone.model.AlphanumericPhoneNumberPageResponse;
import demo.code.phone.model.ValidationResponse;
import demo.code.phone.model.exception.BadRequestException;
import demo.code.phone.model.springdata.Page;
import demo.code.phone.model.springdata.Pageable;
import demo.code.phone.service.PhoneNumberService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/phonenumbers")

// You might not normally do this, or could configure this with security...
@CrossOrigin
public class PhoneNumberResource {

  private PhoneNumberService service;

  public PhoneNumberResource(PhoneNumberService service) {
    this.service = service;
  }

  @GetMapping("/{phoneNumber}/alphanumerics")
  public AlphanumericPhoneNumberPageResponse getAlphanumerics(
      @PathVariable("phoneNumber") String phoneNumber,
      Pageable pageable) {

    if (pageable == null) {
      pageable = new Pageable(0, 10);
    } else if (pageable.getPageSize() > 200) {
      throw new BadRequestException("Page size cannot be more than 200!");
    }

    Page<String> pages =
        service.createAlphaNumerics(phoneNumber, pageable);

    return new AlphanumericPhoneNumberPageResponse(pages);
  }

  // My original front end tests used this endpoint, as I wanted to get front-end & back-end
  // integration going quickly (before tackling).  Used this as an integration point, before
  @GetMapping("/{phoneNumber}/validation")
  public ValidationResponse validateNumber(@PathVariable("phoneNumber") String phoneNumber) {

    boolean isValidPhoneNumber = service.isValidPhoneNumber(phoneNumber);
    return new ValidationResponse(isValidPhoneNumber);
  }


}
