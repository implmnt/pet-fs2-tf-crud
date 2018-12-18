package route
import cats.effect.Sync
import cats.syntax.flatMap._
import cats.syntax.functor._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import service.TodoService

trait TodoRoute[F[_]] extends ProvideRoutes[F]

object TodoRoute {

  class Impl[F[_] : Sync](todoService: TodoService[F]) extends TodoRoute[F] {
    override def routes(dsl: Http4sDsl[F]): HttpRoutes[F] = {
      import dsl._

      HttpRoutes.of {
        case _ @ GET -> Root / "todo" =>
          for {
            todos <- todoService.getAll
            res <- Ok(todos.mkString(","))
          } yield res

        case _ @ GET -> Root / "todo" / IntVar(id) =>
          for {
            todo <- todoService.getById(id)
            res <- todo match {
              case Some(t) => Ok(t.toString)
              case _ => NotFound()
            }
          } yield res
      }
    }
  }

  object Impl {
    def create[F[_] : Sync](todoService: TodoService[F]): TodoRoute[F] =
      new Impl(todoService)
  }
}
