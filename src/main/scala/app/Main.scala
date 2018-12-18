package app
import cats.effect.{ExitCode, IO, IOApp, Sync}
import cats.syntax.flatMap._
import cats.syntax.functor._
import model.Todo
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.syntax.kleisli._
import repository.TodoRepository
import route.TodoRoute
import service.TodoService

object Main extends IOApp {

  val todos: Map[Todo.Id, Todo] = Map(
    1 -> Todo(1, "title1", "text1"),
    2 -> Todo(2, "title2", "text2"),
    3 -> Todo(3, "title3", "text3"),
    4 -> Todo(4, "title4", "text4")
  )

  def createRoutes[F[_] : Sync](dsl: Http4sDsl[F]): F[HttpRoutes[F]] =
    for {
      todoRepository <- TodoRepository.Impl.create(todos)
      todoService <- TodoService.Impl.create(todoRepository)
      todoRoute = TodoRoute.Impl.create(todoService)
      routes = todoRoute.routes(dsl)
    } yield routes

  override def run(args: List[String]): IO[ExitCode] =
    for {
      routes <- createRoutes[IO](org.http4s.dsl.io)
      _ <- BlazeServerBuilder[IO]
        .withHttpApp(routes.orNotFound)
        .bindLocal(9000)
        .serve
        .compile
        .drain
    } yield ExitCode.Success
}
