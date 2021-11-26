package org.example;

import java.util.List;
import java.util.function.Consumer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FundamentalReactiveTypesMain {

  public static void main(String[] args) {
    /*
     * .log() allows to see the events inside the reactive chain
     *
     * Note the "request(unbounded)" call: This is part of the backpressure mechanism
     * An unbounded request means that the producer will forward all that as they are available.
     *
     * Sometimes that can be problematic, for instance, when the consumer is unable to cope
     * with the generated events in a timely manner.
     */

    Flux<String> namesFlux = Flux.fromIterable(List.of("foo", "bar", "alice", "bob")).log();
    System.out.println("flux is ready");

    // Nothing happens until you subscribe!
    namesFlux.subscribe(getStringConsumer());
    System.out.println("flux was subscribed");

    Mono<String> nameMono = Mono.just("something").log();
    System.out.println("mono is ready");

    // Nothing happens until you subscribe!
    nameMono.subscribe(getStringConsumer());
    System.out.println("mono was subscribed");
  }

  private static Consumer<String> getStringConsumer() {
    return name -> System.out.printf("Current value: %s%n", name);
  }
}
