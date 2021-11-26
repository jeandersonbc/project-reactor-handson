package org.example;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class TestingReactiveTypesTest {

  private final TestingReactiveTypes.SomeService service = new TestingReactiveTypes.SomeService();

  /*
   * There are some redundant tests just to illustrate the reactor testing API
   */

  @Test
  void getNamesShouldProduceExpectedQuantity() {
    StepVerifier.create(service.getNames()) // Subscribes to the publisher
        .expectNextCount(3)
        .verifyComplete();
  }

  @Test
  void getNamesShouldProduceElementsInSequence() {
    StepVerifier.create(service.getNames())
        .expectNext("bob", "alice", "john") // Checks that the publisher yields this sequence
        .verifyComplete();
  }

  @Test
  void getNamesShouldReturnFirstFollowedByTwoElements() {
    StepVerifier.create(service.getNames())
        .expectNext("bob") // Checks if this is the first element
        .expectNextCount(2) // checks that the remaining ones are 2 arbitrary elements
        .verifyComplete();
  }

  @Test
  void getNumberShouldReturnValue() {
    StepVerifier.create(service.getNumber()).expectNextCount(1).verifyComplete();
  }
}
