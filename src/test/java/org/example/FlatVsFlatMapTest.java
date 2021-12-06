package org.example;

import java.time.Duration;
import java.util.random.RandomGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FlatVsFlatMapTest {

  @Test
  @DisplayName("A consumer should be able to ingest the expected number of elements")
  void checkNumberOfElements() {
    StepVerifier.create(
            Flux.just("abcd", "efg", "hijlm", "nopqrst")
                // flatMap handles data in an asynchronous way (see the test execution).
                .flatMap(
                    e ->
                        Flux.fromArray(e.split(""))
                            .delayElements(
                                Duration.ofMillis(RandomGenerator.getDefault().nextLong(1000))))
                .log())
        // you can't really predict the sequence due to the delay artificially introduce to
        // demonstrate async calls
        .expectNextCount(19)
        .verifyComplete();
  }
}
