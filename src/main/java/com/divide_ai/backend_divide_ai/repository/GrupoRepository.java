package com.divide_ai.backend_divide_ai.repository;

import com.divide_ai.backend_divide_ai.entidades.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class GrupoRepository {

    private final DynamoDbTable<Grupo> table;

    @Autowired
    public GrupoRepository(DynamoDbEnhancedClient client) {
        this.table = client.table("Grupo", TableSchema.fromBean(Grupo.class));
    }

    public void salvar(Grupo grupo) {
        table.putItem(grupo);
    }

    public Grupo buscarPorId(Long id) {
        return table.getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public void deletar(Long id) {
        table.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}