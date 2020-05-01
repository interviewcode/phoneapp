package demo.code.phone.service;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Generates the "list" of alphanumerics dynamically, starting at the given currentSequencePosition
 * and iterating to the endSequencePosition.  Each sequence position maps uniquely to a given
 * alpahnumeric variation of the original input.
 */
public class AlphaNumericIterator implements Iterator<String> {

  // Applies only to the number we're being asked to generate which is
  // encapsulated (as all possible variations)
  // in the representation of  specificNumberToCharactersByPosition
  private int numberDigits;
  private ArrayList<Integer> endSequencePosition;
  private ArrayList<Integer> currentSequencePosition;
  private char[][] specificNumberToCharactersByPosition;
  private boolean allDone;

  AlphaNumericIterator(int numberDigits,
      ArrayList<Integer> currentSequencePosition,
      ArrayList<Integer> endSequencePosition,
      char[][] specificNumberToCharactersByPosition,
      boolean allDone) {

    assert (numberDigits == 7 || numberDigits == 10);
    assert (endSequencePosition.size() == numberDigits);
    assert (currentSequencePosition.size() == numberDigits);
    assert (specificNumberToCharactersByPosition.length == numberDigits);

    for (int digitPosition = 0; digitPosition < numberDigits; digitPosition++) {
      Integer currentPosition = currentSequencePosition.get(digitPosition);
      Integer endPosition = endSequencePosition.get(digitPosition);
      assert (0 <= currentPosition && currentPosition <= endPosition);
      assert (0 <= endPosition && endPosition <= 5);
    }

    this.allDone = allDone;
    this.numberDigits = numberDigits;
    this.endSequencePosition = endSequencePosition;
    this.currentSequencePosition = currentSequencePosition;
    this.specificNumberToCharactersByPosition = specificNumberToCharactersByPosition;
  }

  //Unusual on an iterator -- but this has the information to answer the question.
  public long totalNumberPossibilities() {
    int total = 1;
    for (int digitPosition = numberDigits - 1; digitPosition >= 0; digitPosition--) {
      char[] optionsAtPosition = specificNumberToCharactersByPosition[digitPosition];
      total = total * optionsAtPosition.length;
    }
    return total;
  }

  @Override
  public boolean hasNext() {
    return !allDone;
  }

  @Override
  public String next() {
    String result = generateFor(currentSequencePosition);
    advanceToNextSequencePosition();
    return result;
  }

  private String generateFor(ArrayList<Integer> currentSequencePosition) {
    StringBuilder sb = new StringBuilder();

    for (int digitPosition = 0; digitPosition < numberDigits; digitPosition++) {
      Integer currentCharPosition = currentSequencePosition.get(digitPosition);

      sb.append(specificNumberToCharactersByPosition[digitPosition][currentCharPosition]);
    }
    return sb.toString();
  }

  private void advanceToNextSequencePosition() {

    // Start in the least significant position and increment, rolling over as necessary.
    for (int digitPosition = numberDigits - 1; digitPosition >= 0; digitPosition--) {
      Integer currentPosition = currentSequencePosition.get(digitPosition);
      Integer endPosition = endSequencePosition.get(digitPosition);

      if (!currentPosition.equals(endPosition)) {

        currentSequencePosition.set(digitPosition, currentPosition + 1);
        return;
      }
      // Roll Over at this position & move to the next position to advance.
      currentSequencePosition.set(digitPosition, 0);
    }
    // If we reach here this is like the odometer rolling over, we've generated the last
    // number in the sequence.
    allDone = true;
  }
}


