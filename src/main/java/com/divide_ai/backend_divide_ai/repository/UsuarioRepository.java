package com.divide_ai.backend_divide_ai.repository;

import com.divide_ai.backend_divide_ai.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class UsuarioRepository {

    private final DynamoDbTable<Usuario> table;

    @Autowired
    public UsuarioRepository(DynamoDbEnhancedClient client) {
        this.table = client.table("Usuario", TableSchema.fromBean(Usuario.class));
    }

    public void salvar(Usuario usuario) {
        table.putItem(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return table.getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public void deletar(Long id) {
        table.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}