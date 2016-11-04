package com.martinhammer.strudel

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.JsonDSL._
import scalaj.http._

object strudel
{

	def main( args: Array[String] )
	{
		if( args.length < 2 )
		{
			System.err.println( "Usage: strudel <hostname> <port>" )
			System.exit(1)
		}

		val conf = new SparkConf().setMaster( "local[2]" ).setAppName( "strudel" )
		val ssc = new StreamingContext( conf, Seconds(10) )

		// input from netcat
		val input = ssc.socketTextStream( args(0), args(1).toInt, StorageLevel.MEMORY_AND_DISK_SER )

		// output
		val output = input.map( "Hello strudel!" + _ )
		output.print()

		// Spark Streaming stuff
		ssc.start()
		ssc.awaitTermination()
	}
}
