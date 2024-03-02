package lc.mine.api.entity;


import lc.mine.api.entity.punishment.Punishment;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.UUID;

@Data
public class    Player {

    @Id
    private UUID uuid;
    private String username;
    private String password;

    @DBRef
    private Rank rank;

    @BsonIgnore
    private Punishment activePunishment;


}
