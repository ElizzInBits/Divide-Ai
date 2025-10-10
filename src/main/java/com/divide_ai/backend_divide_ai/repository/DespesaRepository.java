package com.divide_ai.backend_divide_ai.repository;

import com.divide_ai.backend_divide_ai.entidades.Despesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class DespesaRepository {

    private final DynamoDbTable<Despesa> table;

    @Autowired
    public DespesaRepository(DynamoDbEnhancedClient client) {
        this.table = client.table("Despesa", TableSchema.fromBean(Despesa.class));
    }

    public void salvar(Despesa despesa) {
        table.putItem(despesa);
    }

    public Despesa buscarPorId(Long id) {
        return table.getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public void deletar(Long id) {
        table.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}