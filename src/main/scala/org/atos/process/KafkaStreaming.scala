package org.atos.process

import org.apache.spark.SparkConf
import org.apache._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.kafka._
import org.apache.spark.streaming.dstream.DStream
import _root_.kafka.serializer.StringDecoder

object KafkaStreaming {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").
      setAppName("KafkaReceiver").
      set("spark.rdd.compress","true").
      set("spark.streaming.unpersist", "true")

    val ssc = new StreamingContext(conf, Seconds(10))

    val topicsSet = "Event_topic".split(",").toSet //Topic name is "stream"

    val kafkaParams = Map[String, String]("metadata.broker.list" -> "localhost:9092")

    val directKafkaStream = KafkaUtils.
      createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topicsSet)
    directKafkaStream.print()

    ssc.start
    ssc.awaitTermination

  }
}
