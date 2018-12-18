package model
import model.Todo.Id

case class Todo(id: Id, title: String, text: String)

object Todo {

  type Id = Int
}