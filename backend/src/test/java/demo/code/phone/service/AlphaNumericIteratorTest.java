package demo.code.phone.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import demo.code.phone.model.springdata.Pageable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;


/**
 * The Scenario Test allows for DRY (each test has the same setup, run, and evaluation) allowing us
 * to cleanly run several tests.
 */

// Developer Comment about problem and test setup:
//
//     I normally program in Kotlin which allows for named arguments which makes "what" each
//     scenario is doing to be more clear.
//    
//     Example:
//     {code}
//
//      TestScenario(givenInput="1010302", totalNumberPossibilities=16,
//                 expectedOutput=listOf( "1010E0B", "1010E0C","1010F02", "1010F0A", "1010F0B", "1010F0C")
//      ).startOutputAtPage(2),
//
//     {code}
//    
//      Unfortunately Java doesn't use named parameters so you have to remember what the few positional
//      arguments represent.
//    
//       new TestScenario("1010302", 16,
//                 "1010E0B", "1010E0C",
//                 "1010F02", "1010F0A", "1010F0B", "1010F0C"
//       ).startOutputAtPage(2),
//
//     While concise, it does not read as well unfortunately.

class AlphaNumericIteratorTest {

  private static int PAGE_SIZE = 5;

  @Test
  void pastEndOfInputPagingTest() {
    AlphaNumericIterator iterator =
        new AlphaNumericIteratorBuilder("9999999999")
            .startAtPage(new Pageable(1953125, PAGE_SIZE))
            .build();

    assertFalse(iterator.hasNext());
  }

  @TestFactory
  List<DynamicTest> phoneNumberTests() {

    return getTestScenarios()
        .stream()
        .map(scenario -> DynamicTest
            .dynamicTest(
                "TESTING Generation for: " +
                    scenario.getGivenInput() + " page: " + scenario.getPageNumber(),
                () -> {

                  // Builder always starts at the beginning
                  AlphaNumericIteratorBuilder builder =
                      new AlphaNumericIteratorBuilder(scenario.getGivenInput());

                  // If we're testing paging, we start not at the beginning...
                  // but at an expected point somewhere through the iteration
                  if (scenario.getPageNumber() > 0) {
                    builder = builder.startAtPage(
                        new Pageable(scenario.getPageNumber(), PAGE_SIZE)
                    );
                  }

                  AlphaNumericIterator iterator = builder.build();

                  assertEquals(scenario.getTotalNumberPossibilities(),
                      iterator.totalNumberPossibilities());

                  for (int i = 0; i < scenario.getExpectedOutput().size(); i++) {
                    assertEquals(scenario.getExpectedOutput().get(i), iterator.next(),
                        "Was comparing the " + i + "th expected result");
                  }
                })
        ).collect(Collectors.toList());
  }

  private List<TestScenario> getTestScenarios() {

    // First parameter is the input, 2nd # of possibilities total,
    // 3 the expected generation sequence...
    return Arrays.asList(
        // 7 Digit
        new TestScenario("1111111", 1,
            "1111111"
        ),
        // 10 Digit
        new TestScenario("1111111111", 1,
            "1111111111"
        ),
        // With formatting
        new TestScenario("(111)1111111", 1,
            "1111111111"
        ),
        new TestScenario("(111)111-1111", 1,
            "1111111111"
        ),
        new TestScenario("(111) 111-1111", 1,
            "1111111111"
        ),
        new TestScenario("111 111 1111", 1,
            "1111111111"
        ),
        new TestScenario("111-111-1111", 1,
            "1111111111"
        ),

        // -- Testing Generation --
        new TestScenario("1010102", 4,
            "1010102", "101010A", "101010B", "101010C"
        ),
        new TestScenario("1010302", 16,
            "1010302", "101030A", "101030B", "101030C",
            "1010D02", "1010D0A", "1010D0B", "1010D0C",
            "1010E02", "1010E0A", "1010E0B", "1010E0C",
            "1010F02", "1010F0A", "1010F0B", "1010F0C"
        ),
        // 1048576 = 4 ^ 10
        new TestScenario("5555555555", 1048576,
            "5555555555", "555555555J", "555555555K", "555555555L",
            "55555555J5", "55555555JJ", "55555555JK", "55555555JL",
            "55555555K5", "55555555KJ", "55555555KK", "55555555KL",
            "55555555L5", "55555555LJ", "55555555LK", "55555555LL",
            "5555555J55", "5555555J5J", "5555555J5K", "5555555J5L"
        ),

        // 9765625 = 5^10
        new TestScenario("9999999999", 9765625,
            "9999999999", "999999999W", "999999999X", "999999999Y", "999999999Z",
            "99999999W9", "99999999WW", "99999999WX", "99999999WY", "99999999WZ",
            "99999999X9", "99999999XW", "99999999XX", "99999999XY", "99999999XZ",
            "99999999Y9", "99999999YW", "99999999YX", "99999999YY", "99999999YZ",
            "99999999Z9", "99999999ZW", "99999999ZX", "99999999ZY", "99999999ZZ"

        ),
        new TestScenario("1000302", 16,
            "1000D0A", "1000D0B", "1000D0C",
            "1000E02", "1000E0A"
        ).startOutputAtPage(1),
        new TestScenario("1010302", 16,
            "1010E0B", "1010E0C",
            "1010F02", "1010F0A", "1010F0B", "1010F0C"
        ).startOutputAtPage(2),
        new TestScenario("1010302", 16,
            "1010F0C"
        ).startOutputAtPage(3),
        new TestScenario("9WXYZ9WXYZ", 9765625,
            "ZZZZZZZZZ9", "ZZZZZZZZZW", "ZZZZZZZZZX", "ZZZZZZZZZY", "ZZZZZZZZZZ"
        ).startOutputAtPage(1953124), // The Last Page
        // 1048576 = 4 ^ 10 -- At the 209715 page there is 1 result left over.
        new TestScenario("5555555555", 1048576,
            "LLLLLLLLLL"
        ).startOutputAtPage(209715)
    );

  }
}


final class TestScenario {

  private String givenInput;
  private int totalNumberPossibilities;
  private List<String> expectedOutput;

  private int pageNumber = 0;


  /**
   * @param expectedOutputArray - This may only the beginning of the sequence ( e.g. for several
   * thousand results we only check the first few).
   */
  TestScenario(String givenInput, int totalNumberPossibilities,
      String... expectedOutputArray) {
    this.givenInput = givenInput;
    this.totalNumberPossibilities = totalNumberPossibilities;
    this.expectedOutput = Arrays.asList(expectedOutputArray);
  }

  TestScenario startOutputAtPage(int pageNumber) {
    this.pageNumber = pageNumber;
    return this;
  }


  String getGivenInput() {
    return givenInput;
  }

  int getTotalNumberPossibilities() {
    return totalNumberPossibilities;
  }

  List<String> getExpectedOutput() {
    return expectedOutput;
  }

  int getPageNumber() {
    return pageNumber;
  }
}