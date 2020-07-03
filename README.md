# scalacheck-faker
![](https://github.com/etspaceman/scalacheck-faker/workflows/Scala%20CI/badge.svg)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.etspaceman/scalacheck-faker_2.13.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.etspaceman/scalacheck-faker_2.13)

Fake Data Generation using ScalaCheck

This library ports over common Faker routines from popular implementations, 
such as [Java Faker](https://github.com/DiUS/java-faker) into a Scala library.

# Arbitraries
Data generators are provided in the form of [ScalaCheck Arbitraries](https://www.scala-exercises.org/scalacheck/arbitrary).
In the below example, we use the instances to construct an Arbitrary instance for our own class.

```scala
import org.scalacheck._

import faker._
// Import the default resource-loader implicits
import faker.ResourceLoader.Implicits._

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
The above uses `import faker.ResourceLoader.Implicits._`, which provides the default ResourceLoader for the
Arbitrary instances. This ResourceLoader will evaluate the Faker instances against your system's default
Locale value. You can override this value in your application's JVM 
[through JVM args](https://docs.oracle.com/cd/E23507_01/Platform.20073/ATGProgGuide/html/s1809settingthejavavirtualmachineloca01.html).

If you have a need to use a separate locale than the system default, you can create your own instance:

```scala
import java.util.Locale

import org.scalacheck._

import faker._
import faker.ResourceLoader

object UKResourceLoader {
  implicit val UKResourceLoader: ResourceLoader = new ResourceLoader(Locale.UK)
}
```

## Faker Class
Another option for generating data is the [Faker class](src/main/scala/faker/Faker.scala). This class
provides a similar feel to other Faker libraries.

```scala
import java.time.Instant
import java.util.Locale

import faker._

val firstName: String = Faker.default.firstName()
val nowInstant: Instant = Faker.default.nowInstant()

// You can also construct a Faker instance using a manually-provided Locale:
val ukFaker: Faker = new Faker(Locale.UK)

val ukFirstName: String = ukFaker.firstName()
```

## Early Development Warning
This repository is in active and early development. Use at your own risk.
