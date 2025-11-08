package com.divide_ai.backend_divide_ai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

// @Component // Desabilitado - usando SimpleDataSeeder
@Order(0)
public class DynamoDbTableCreator implements CommandLineRunner {

    @Autowired
    private DynamoDbClient dynamoDbClient;

    @Override
    public void run(String... args) {
        createTableIfNotExists("Usuario");
        createTableIfNotExists("Grupo");
        createTableIfNotExists("Despesa");
    }

    private void createTableIfNotExists(String tableName) {
        try {
            dynamoDbClient.describeTable(DescribeTableRequest.builder().tableName(tableName).build());
            System.out.println("✅ Tabela " + tableName + " já existe");
        } catch (ResourceNotFoundException e) {
            createTable(tableName);
            System.out.println("✅ Tabela " + tableName + " criada com sucesso");
        }
    }

    private void createTable(String tableName) {
        CreateTableRequest request = CreateTableRequest.builder()
                .tableName(tableName)
                .keySchema(KeySchemaElement.builder()
                        .attributeName("id")
                        .keyType(KeyType.HASH)
                        .build())
                .attributeDefinitions(AttributeDefinition.builder()
                        .attributeName("id")
                        .attributeType(ScalarAttributeType.N)
                        .build())
                .billingMode(BillingMode.PAY_PER_REQUEST)
                .build();

        dynamoDbClient.createTable(request);
    }
}