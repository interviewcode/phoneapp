package demo.code.phone.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PhoneNumberValidatorServiceTest {

  @Autowired
  private PhoneNumberService service;

  @TestFactory
  List<DynamicTest> phoneNumberTests() {

    return getPhonScenarios()
        .stream()
        .map(scenario -> DynamicTest.dynamicTest(scenario.getName(),
            () -> {
              // Expect this not to throw an exception.
              boolean actualResult = service.isValidPhoneNumber(scenario.getInput());
              assertEquals(scenario.expectValid(), actualResult);

            })
        ).collect(Collectors.toList());

  }


  private List<PhoneTestScenario> getPhonScenarios() {
    return Arrays.asList(
        new PhoneTestScenario("Invalid # - too short", "123-456-789"),
        new PhoneTestScenario("Invalid # - bad symbol", "123@456-789"),
        new PhoneTestScenario("Invalid # - too long", "123-456-78900"),
        new PhoneTestScenario("Invalid # - bad format 1", "123-45-7890"),
        new PhoneTestScenario("Invalid # - bad format 2", "123-45-67890"),

        new PhoneTestScenario("Valid 10 digit# with extra leading spaces", " 123-456-7890"),
        new PhoneTestScenario("Valid 10 digit# with extra leading tab", "\t123-456-7890"),
        new PhoneTestScenario("Valid 10 digit# with extra trailing spaces", " 123-456-7890"),
        new PhoneTestScenario("Valid 10 digit# with extra trailing tab", "123-456-7890\t"),
        new PhoneTestScenario("Valid 10 digit# with dashes", "123-456-7890"),
        new PhoneTestScenario("Valid 10 digit# with dashes & parens", "(123)-456-7890"),
        new PhoneTestScenario("Valid 10 digit# with parens & spaces", "(123) 456 7890"),
        new PhoneTestScenario("Valid 10 digit# no spacing", "1234567890")
    );
  }

}


class PhoneTestScenario {

  private String name;
  private String input;

  PhoneTestScenario(String name, String input) {
    this.name = name;
    this.input = input;
  }

  String getName() {
    return name;
  }

  String getInput() {
    return input;
  }

  boolean expectValid() {
    return !name.contains("Invalid");
  }

}

