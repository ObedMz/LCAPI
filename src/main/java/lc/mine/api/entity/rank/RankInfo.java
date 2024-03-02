package lc.mine.api.entity.rank;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.Instant;
import java.util.UUID;

@Data
public class RankInfo {
    @Id
    private UUID uuid;
    @DBRef
    private Rank rank;
    private Boolean permanent;
    private Boolean hide;
    private String userColor;
    private Instant dateInstant;
    private Instant expiresInstant;
}
