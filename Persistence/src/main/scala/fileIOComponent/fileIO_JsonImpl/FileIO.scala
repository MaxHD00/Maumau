package fileIOComponent.fileIO_JsonImpl

import fileIOComponent.{FileIOComponent, FileIOInterface}
import play.api.libs.json.{JsValue, Json}
import com.google.inject.{Guice, Inject, Injector}

import java.io.*
import scala.io.Source
import scala.io.*
import databaseComponent.*

class FileIO extends FileIOInterface {

  val injector: Injector = Guice.createInjector(new FileIOComponent)
  val database: DatabaseInterface = injector.getInstance(classOf[DatabaseInterface])

  override def save(gameState: String): Unit = {
    database.writeTable(gameState)
    database.printDB()
  }

  override def load(): String = {
    database.readTable()
  }

/*
  val file = new File("save.json")

  override def save(gameState: String): Unit = {
    println(gameState)
    val pw = new PrintWriter(new File("save.json"))
    pw.write(gameState)
    pw.close()
  }


  override def load(): String = {
    val source: BufferedSource = Source.fromFile("save.json")
    val gameState: String = source.getLines.mkString
    source.close()
    gameState
  }
*/
}
