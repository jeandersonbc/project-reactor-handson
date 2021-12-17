package org.example;

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

  @Test
  @DisplayName("Should return empty response from the reactive chain")
  void checkEmptyResponse() {
    StepVerifier.create(BasicReactiveTransformations.namesMono_map_filter(4))
        .expectNext("")
        .verifyComplete();
  }

  @Test
  @DisplayName("Should return from alternative publisher if empty")
  void checkEmptyResponse2() {
    StepVerifier.create(BasicReactiveTransformations.namesMono_map_filter2(4))
        .expectNext(
            "default") // this value comes from a different publisher. See the implementation for
                       // further details
        .verifyComplete();
  }
}
