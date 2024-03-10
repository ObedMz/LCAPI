package lc.mine.api.entity.punishment;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.UUID;

@Data
public class Punishment {
    @Id
    private UUID id;
    private UUID punisher;

    private String reason;

    private Instant issuedInstant;

    private Instant expiresInstant;

    private Boolean active;

    private Boolean permanent;

    private String ip;
    private UUID player;
    private Boolean isIP;
    private PunishmentType type;


}
