package org.example;

import reactor.core.publisher.Mono;

public class BasicReactiveTransformations {

  public static Mono<String> filterMapExample(int criterion) {
    return Mono.just("alex").filter(e -> e.length() >= criterion).map(String::toUpperCase);
  }
}
