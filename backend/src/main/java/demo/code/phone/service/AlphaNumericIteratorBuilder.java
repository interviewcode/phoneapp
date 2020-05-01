package demo.code.phone.service;


import demo.code.phone.model.springdata.Pageable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class must determine where based on the input string and the paging position, what "index"
 * into the list we're starting at.
 *
 * The builder pattern is used -- if the #startAtPage method isn't used, the iterator starts
 * (as you would expect) at the beginning.
 */
public class AlphaNumericIteratorBuilder {

  // Applies always (to every possible number)
  static private char[][] alphanumericMapping = {
      {'0'},
      {'1'},
      {'2', 'A', 'B', 'C'},
      {'3', 'D', 'E', 'F'},
      {'4', 'G', 'H', 'I'},
      {'5', 'J', 'K', 'L'},
      {'6', 'M', 'N', 'O'},
      {'7', 'P', 'Q', 'R', 'S'},
      {'8', 'T', 'U', 'V'},
      {'9', 'W', 'X', 'Y', 'Z'}
  };

  // Maps every possible alphanumeric character to its equivalent options.
  static HashMap<Character, char[]> inputToCharOptionsMap = new HashMap<>();

  static {
    // Every digit equivalent can map to every other option...
    for (char[] optionsForDigit : alphanumericMapping) {
      for (char digitEquivalent : optionsForDigit) {
        inputToCharOptionsMap.put(digitEquivalent, optionsForDigit);
        inputToCharOptionsMap.put(Character.toLowerCase(digitEquivalent), optionsForDigit);
      }
    }
  }

  private int numberDigits;
  private ArrayList<Integer> endSequencePosition;
  private ArrayList<Integer> beginSequencePosition;
  private char[][] specificNumberToOptionsByPosition;

  /**
   * Requires a "valid" number of 7-10 digits. Some characters (space, parentheses and dashes are
   * allowed as extras, but will be stripped out)
   */
  public AlphaNumericIteratorBuilder(String inputNumber) {

    String cleanNumber = inputNumber.replaceAll("[()\\- ]", "");

    numberDigits = cleanNumber.length();
    assert (numberDigits == 7 || numberDigits == 10);

    beginSequencePosition = new ArrayList<>(numberDigits);
    endSequencePosition = new ArrayList<>(numberDigits);
    specificNumberToOptionsByPosition = new char[numberDigits][];

    for (int digitPosition = 0; digitPosition < numberDigits; digitPosition++) {
      char ch = cleanNumber.charAt(digitPosition);
      char[] digitOptions = inputToCharOptionsMap.get(ch);

      endSequencePosition.add(digitOptions.length - 1);
      beginSequencePosition.add(0);
      specificNumberToOptionsByPosition[digitPosition] = digitOptions;
    }
  }

  public AlphaNumericIteratorBuilder startAtPage(Pageable page) {

    long startIndex = page.getPageSize() * page.getPageNumber();

    // The key here is to adjust the starting position to be what the startIndex represents.

    if (startIndex == 0) {
      // Already start to start position.
      return this;
    }

    if (startIndex >= totalNumberPossibilities()) {
      //we're done before we begin...
      beginSequencePosition = endSequencePosition;
      return this;
    }

    // TRUE: 0 < startIndex < totalNumberPossibilities
    // This guarantees termination...

    int digitPosition = numberDigits - 1;

    while (startIndex != 0 && digitPosition >= 0) {
      char[] digitOptions = specificNumberToOptionsByPosition[digitPosition];
      long optionPositionAtDigitPosition = startIndex % digitOptions.length;

      beginSequencePosition
          .set(digitPosition, Long.valueOf(optionPositionAtDigitPosition).intValue());

      // Make Progress
      startIndex = startIndex / digitOptions.length;
      digitPosition--;
    }

    return this;
  }

  private long totalNumberPossibilities() {
    int total = 1;
    for (int digitPosition = numberDigits - 1; digitPosition >= 0; digitPosition--) {
      char[] optionsAtPosition = specificNumberToOptionsByPosition[digitPosition];
      total = total * optionsAtPosition.length;
    }
    return total;
  }


  public AlphaNumericIterator build() {
    //Identity is important in this test...
    boolean allDone = (beginSequencePosition == endSequencePosition);

    return new AlphaNumericIterator(
        numberDigits,
        beginSequencePosition,
        endSequencePosition,
        specificNumberToOptionsByPosition,
        allDone
    );
  }
}