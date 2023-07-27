package faker

abstract class Newtype[A] { self =>
  opaque type Type = A

  def apply(a: A): Type = a

  extension (orig: Type) def value: A = orig

  def unapply(orig: Type): Some[A] = Some(orig.value)
}
