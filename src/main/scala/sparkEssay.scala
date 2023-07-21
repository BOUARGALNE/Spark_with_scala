import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.expr
object sparkEssay {
  def main(args: Array[String]):Unit={
  val spark = SparkSession.builder().master("local[3]")
    .appName("my first project in scala")
    .config("spark.streaming.stopGracefullyOnShutdown","true")
    .config("spark.sql.shuffle.partitions",3)
    .getOrCreate()
     val linesDF = spark.readStream
       .format("socket")
       .option("host","localhost")
       .option("port","9999")
       .load()
    linesDF.printSchema()
    val wordDF=linesDF.select(expr("explode(split(value,' ')) as word"))
    val countDF =wordDF.groupBy("word").count()
    val wordCountQuery = countDF.writeStream
      .format("console")
      .option("checkpointLocation","chk-point-dir")
      .outputMode("complete")
      .start()
    wordCountQuery.awaitTermination()
  }
}