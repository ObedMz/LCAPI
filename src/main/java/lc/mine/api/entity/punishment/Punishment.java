package lc.mine.api.entity.punishment;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class Punishment {


    private UUID punisher;

    private String reason;

    private Instant issuedInstant;

    private Instant expiresInstant;

    private Boolean active;

    private PunishmentType type;


}
