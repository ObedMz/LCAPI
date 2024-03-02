package lc.mine.api.entity.rank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.Instant;
import java.util.UUID;

public class RankInfo {
    @Id
    private UUID uuid;
    @DBRef
    private Rank rank;
    private boolean permanent;
    private boolean hide;
    private String userColor;
    private Instant dateInstant;
    private Instant expiresInstant;
}
