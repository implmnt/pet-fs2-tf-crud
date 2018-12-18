package route
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

trait ProvideRoutes[F[_]] {

  def routes(dsl: Http4sDsl[F]): HttpRoutes[F]
}
