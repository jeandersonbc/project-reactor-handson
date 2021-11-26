package org.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TestingReactiveTypes {

  // Let's say we have this dummy service... check the counterpart test file for tests
  public static class SomeService {

    public Flux<String> getNames() {
      return Flux.just("bob", "alice", "john");
    }

    public Mono<Integer> getNumber() {
      return Mono.just(47);
    }
  }
}
