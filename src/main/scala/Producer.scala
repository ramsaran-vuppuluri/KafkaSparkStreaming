import java.util.Properties

import factory.KafkaProps
import org.apache.kafka.clients.producer._
import org.apache.kafka.common.errors.{SerializationException, TimeoutException}


object Producer extends App {
  val kafkaProps: Properties = new KafkaProps(bootstrapServers = "localhost:9090,localhost:9091", keySerializer = "org.apache.kafka.common.serialization.StringSerializer", valueSerializer = "org.apache.kafka.common.serialization.StringSerializer").instantiateKafkaProps

  val kafkaProducer = new KafkaProducer[String, String](kafkaProps)

  var producerRecord: ProducerRecord[String, String] = new ProducerRecord[String, String]("CustomerCountry", "Precision Products", "France")

  try {
    kafkaProducer.send(producerRecord).get

    producerRecord = new ProducerRecord[String, String]("CustomerCountry", "Biomedical Materials", "USA")

    kafkaProducer.send(producerRecord, new DemoProducerCallback)
  } catch {
    case e: SerializationException => e.printStackTrace
    case e: BufferExhaustedException => e.printStackTrace
    case e: TimeoutException => e.printStackTrace
  }
}

class DemoProducerCallback extends Callback {
  override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
    if (exception != null) exception.printStackTrace
  }
}