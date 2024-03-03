package lc.mine.api.entity;


import lc.mine.api.entity.auth.Login;
import lc.mine.api.entity.punishment.Punishment;
import lc.mine.api.entity.rank.Rank;
import lc.mine.api.entity.rank.RankInfo;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.UUID;

@Data
public class Player {
    @Id
    private UUID uuid;
    private boolean premium;
    private String username;
    private String password;
    private int coins;
    private int vipPoints;
    private RankInfo rankInfo;
    @DBRef
    private Login authInfo;
    @BsonIgnore
    private Punishment activePunishment;


}
