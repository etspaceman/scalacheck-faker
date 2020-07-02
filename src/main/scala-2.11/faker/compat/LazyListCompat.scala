package faker.compat

trait LazyListCompat {
  type LazyList[A] = Stream[A]
  val LazyList = Stream
}