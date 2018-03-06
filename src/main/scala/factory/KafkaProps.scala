package factory

import java.util.Properties

class KafkaProps(bootstrapServers: String, keySerializer: String, valueSerializer: String) {
  def instantiateKafkaProps: Properties = {
    val kafkaProps = new Properties()
    kafkaProps.put("bootstrap.servers", bootstrapServers)
    kafkaProps.put("key.serializer", keySerializer)
    kafkaProps.put("value.serializer", valueSerializer)
    kafkaProps
  }
}