package demo.code.phone.service;

import demo.code.phone.model.exception.BadRequestException;
import demo.code.phone.model.springdata.Page;
import demo.code.phone.model.springdata.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberService {

  private String OPTIONAL_SEPARATOR = "[- ]?";
  private String ALLOWED_NUMBERS = "[0-9A-Za-z]";
  private String OPTIONAL_PREFIX =
      "(\\(?" + ALLOWED_NUMBERS + "{3}\\)?" + OPTIONAL_SEPARATOR + ")?";

  // This should match what the front end has.. (what the front end has is a copy of this expression)
  private Pattern phoneRegex = Pattern.compile(
      OPTIONAL_PREFIX + ALLOWED_NUMBERS + "{3}" + OPTIONAL_SEPARATOR + ALLOWED_NUMBERS + "{4}");


  /**
   * Throws an exception if the input isn't as required/expected. Try to give some helpful
   * messaging.
   */
  public boolean isValidPhoneNumber(String phoneNumberGiven) {
    if (phoneNumberGiven == null || phoneNumberGiven.trim().equals("")) {
      throw new BadRequestException("No input given!");
    }

    String validateThisNumber = phoneNumberGiven.trim();

    var matcher = phoneRegex.matcher(validateThisNumber);
    return matcher.matches();
  }


  /**
   * The formulation of this method is towards what a repository might look like.
   * However we're going to generate the numbers algorithmically...
   */
  public Page<String> createAlphaNumerics(String phoneNumber, Pageable pageable) {

    if (!isValidPhoneNumber(phoneNumber)) {
      throw new BadRequestException("Invalid phone number given: " + phoneNumber);
    }

    AlphaNumericIterator resultIterator = new AlphaNumericIteratorBuilder(phoneNumber)
        .startAtPage(pageable).build();

    List<String> stringList = new ArrayList<>(pageable.getPageSize());
    for (int i = 0; i < pageable.getPageSize() && resultIterator.hasNext(); i++) {
      stringList.add(resultIterator.next());
    }

    return new Page<String>(stringList, pageable, resultIterator.totalNumberPossibilities());
  }
}
