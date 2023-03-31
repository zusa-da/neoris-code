package com.neoris.entities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@MongoEntity(collection="clientes")
public class Cliente {
    public ObjectId id; // used by MongoDB for the _id field
    public String name;
    public Long birth;
    public Boolean status;
}