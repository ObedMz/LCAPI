package lc.mine.api.entity;


import lc.mine.api.entity.auth.Auth;
import lc.mine.api.entity.punishment.Punishment;
import lc.mine.api.entity.rank.RankInfo;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
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

    private Auth authInfo;
    @BsonIgnore
    private List<Punishment> punishmentList = new ArrayList<>();
}
