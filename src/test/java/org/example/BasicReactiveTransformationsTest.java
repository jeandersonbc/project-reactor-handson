package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class BasicReactiveTransformationsTest {

  @Test
  @DisplayName("Reactive chain must meet the filtering criterion")
  void checkTransformations() {

    int matches = 3;
    Mono<String> result = BasicReactiveTransformations.filterMapExample(matches);
    StepVerifier.create(result).expectNext("ALEX").verifyComplete();

    int mismatches = 999;
    result = BasicReactiveTransformations.filterMapExample(mismatches);
    StepVerifier.create(result).expectNextCount(0).verifyComplete();
  }
}
