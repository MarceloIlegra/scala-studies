
import scala.sys.process._
import java.io.File

object Main {
  
  def main(args: Array[String]): Unit = {
    print("running bash command \n")
    
    val log_dir = "/home/marceloserpa/test/"
    
    ("pwd" #> new File(s"$log_dir/shell-redirect.log")).!
    
    print("running bash command \n\n\n")
  }
  
  
}