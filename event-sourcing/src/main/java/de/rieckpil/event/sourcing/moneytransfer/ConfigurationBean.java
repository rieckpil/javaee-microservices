/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.event.sourcing.moneytransfer;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.resource.ConnectionFactoryDefinition;
import javax.resource.spi.TransactionSupport;

@ConnectionFactoryDefinition(name = "java:module/env/KafkaConnectionFactory",
        description = "Kafka Connection Factory",
        interfaceName = "fish.payara.cloud.connectors.kafka.KafkaConnectionFactory",
        resourceAdapter = "kafka-rar-0.1.0",
        minPoolSize = 2,
        maxPoolSize = 2,
        transactionSupport = TransactionSupport.TransactionSupportLevel.NoTransaction,
        properties = {
            "bootstrapServersConfig=localhost:9092",
            "clientId=PayaraMicroMessenger"
        })
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class ConfigurationBean {

}
