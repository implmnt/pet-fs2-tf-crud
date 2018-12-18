package repository
import cats.effect.Sync
import model.Todo
import model.Todo.Id

trait TodoRepository[F[_]] {

  def all: F[List[Todo]]
  def get(id: Todo.Id): F[Option[Todo]]
}

object TodoRepository {

  class Impl[F[_]](todos: Map[Todo.Id, Todo])(implicit F: Sync[F]) extends TodoRepository[F] {
    override def all: F[List[Todo]] = F.pure(todos.values.toList)
    override def get(id: Id): F[Option[Todo]] = F.pure(todos.get(id))
  }

  object Impl {
    def create[F[_]](state: Map[Todo.Id, Todo] = Map.empty)(implicit F: Sync[F]): F[TodoRepository[F]] =
      F.delay {
        new Impl(state)
      }
  }
}
