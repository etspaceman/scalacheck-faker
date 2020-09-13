package faker.music

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class MusicAlbum private (value: String) extends AnyVal

object MusicAlbum {
  def musicAlbums(implicit loader: ResourceLoader): Seq[MusicAlbum] =
    loader.loadKey[Seq[MusicAlbum]]("music.albums")

  implicit def musicAlbumArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[MusicAlbum] =
    Arbitrary(Gen.oneOf(musicAlbums))

  implicit val musicAlbumConfigReader: ConfigReader[MusicAlbum] = deriveReader
}
