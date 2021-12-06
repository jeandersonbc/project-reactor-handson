package org.example;

import java.time.Duration;
import java.util.List;
import java.util.random.RandomGenerator;
import reactor.core.publisher.Flux;

public class FlatVsFlatMap {

  // Suppose we have a sequence of strings...
  private static final List<String> someSequence = List.of("abcd", "efg", "hijlm", "nopqrst");

  // ...and reactive type that publishes that sequence
  public static Flux<String> fetchData() {
    return Flux.fromIterable(someSequence);
  }

  public static void main(String[] args) {
    fetchData().subscribe(System.out::println);

    // map() is an operator that works SYNCHRONOUSLY (i.e., applies the transformation sequentially)
    // it's very normally used for 1-to-1 transformations.
    fetchData().map(String::toUpperCase).subscribe(System.out::println);

    // flatMap, however, differs in 2 aspects: (1) unfolds the input to a reactive sequence (i.e.,
    // Flux)...
    fetchData().flatMap(e -> Flux.fromArray(e.split(""))).subscribe(System.out::println);

    // ...and due to the async nature of Flux, it works asynchronously. i.e., each transformation is
    // applied asynchronously and available on the downstream chain as they become available.
    fetchData()
        .flatMap(
            e ->
                Flux.fromArray(e.split(""))
                    .delayElements(Duration.ofMillis(RandomGenerator.getDefault().nextLong(1000))))
        .log()
        .subscribe(System.out::println);

    // note that main returns, and we don't see the output from the flatMap
    // you can use a test case to verify that this last flatmap returns the expected number of elements!
    // see org.example.FlatVsFlatMapTest
  }
}
