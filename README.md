# scalacheck-faker
![](https://github.com/etspaceman/scalacheck-faker/workflows/Scala%20CI/badge.svg)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.etspaceman/scalacheck-faker_2.13.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.etspaceman/scalacheck-faker_2.13)
[![codecov](https://codecov.io/gh/etspaceman/scalacheck-faker/branch/master/graph/badge.svg)](https://codecov.io/gh/etspaceman/scalacheck-faker)
[![Scala Steward badge](https://img.shields.io/badge/Scala_Steward-helping-blue.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=)](https://scala-steward.org) [![Join the chat at https://gitter.im/etspaceman/scalacheck-faker](https://badges.gitter.im/etspaceman/scalacheck-faker.svg)](https://gitter.im/etspaceman/scalacheck-faker?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Mergify Status](https://img.shields.io/endpoint.svg?url=https://gh.mergify.io/badges/<owner>/<repo>&style=flat)](https://mergify.io)

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
  implicit val UKResourceLoader: ResourceLoader = ResourceLoader.en_GB
}
```

## Faker Class
Another option for generating data is the [Faker class](src/main/scala/faker/Faker.scala). This class
provides a similar feel to other Faker libraries.

```scala
import java.time.Instant
import java.util.Locale

import org.scalacheck.rng.Seed

import faker._

val firstName: String = Faker.default.firstName()
val nowInstant: Instant = Faker.default.nowInstant()

// You can also leverage a Faker instance using a manually-designated Locale:
val ukFirstName: String = Faker.en_GB.firstName()

// Sometimes you need to be able to manually supply the seed for the random number generator.
// You can do that below:
val firstNameSeeded: String = Faker.default.firstName(Seed(2L))
```

## Un-implemented instances
Some locales may cause Faker to return dummy values for certain instances. For example,
there are no `StateLike` instances for the UK (en_GB) Locale. 

Some instances may not be implemented due to the support not being present in some other
Faker library. If you see some instances that should exist for a locale, and have values
that would be relevant for it, please either open an Issue or PR with the proposed
implementation.
