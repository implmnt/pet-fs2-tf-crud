package service
import cats.effect.Sync
import model.Todo
import model.Todo.Id
import repository.TodoRepository

trait TodoService[F[_]] {

  def getAll: F[List[Todo]]
  def getById(id: Todo.Id): F[Option[Todo]]
}

object TodoService {

  class Impl[F[_]](todoRepository: TodoRepository[F]) extends TodoService[F] {
    override def getAll: F[List[Todo]] = todoRepository.all
    override def getById(id: Id): F[Option[Todo]] = todoRepository.get(id)
}

  object Impl {
    def create[F[_]](todoRepository: TodoRepository[F])(implicit F: Sync[F]): F[TodoService[F]] =
      F.delay {
        new Impl[F](todoRepository)
      }
  }
}
