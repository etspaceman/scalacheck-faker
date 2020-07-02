# scalacheck-faker
![](https://github.com/etspaceman/scalacheck-faker/workflows/Scala%20CI/badge.svg)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.etspaceman/scalacheck-faker/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/io.github.etspaceman/scalacheck-faker)

Fake Data Generation using ScalaCheck

This library ports over common Faker routines from popular implementations, 
such as [Java Faker](https://github.com/DiUS/java-faker) into a Scala library.

Data generators are provided in the form of [ScalaCheck Arbitraries](https://www.scala-exercises.org/scalacheck/arbitrary).
Below is some example usage:

```scala
import faker._

import org.scalacheck._

final case class MyClass(firstName: String, lastName: String, emailAddress: String, ipAddress: String)

object MyClass {
  implicit val myClassArbitrary: Arbitrary[MyClass] = Arbitrary(
    for {
      firstName <- Arbitrary.arbitrary[name.FirstName]
      lastName <- Arbitrary.arbitrary[name.LastName]
      email <- Arbitrary.arbitrary[internet.EmailAddress]
      ip <- Arbitrary.arbitrary[internet.IpV4Address]
    }  yield MyClass(firstName.value, lastName.value, email.value, ip.value)
  )
}
```

## Early Development Warning
This repository is in active and early development. Use at your own risk.
