package org.example;

import reactor.core.publisher.Mono;

public class BasicReactiveTransformations {

  public static Mono<String> filterMapExample(int criterion) {
    return Mono.just("alex").filter(e -> e.length() >= criterion).map(String::toUpperCase);
  }

  /*

  defaultIfEmpty X switchIfEmpty

  In summary, one returns an actual default value (defaultIfEmpty) while the other
  returns a "fallback" publisher.

   */
  public static Mono<String> namesMono_map_filter(int stringLength) {
    return Mono.just("alex")
        .filter(e -> e.length() > stringLength)
        .map(String::toUpperCase)
        .defaultIfEmpty("")
        .log();
  }

  public static Mono<String> namesMono_map_filter2(int stringLength) {
    return Mono.just("alex")
        .filter(e -> e.length() > stringLength)
        .map(String::toUpperCase)
        .switchIfEmpty(Mono.just("default"))
        .log();
  }
}
