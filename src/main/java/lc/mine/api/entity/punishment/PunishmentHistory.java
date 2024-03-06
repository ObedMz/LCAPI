package lc.mine.api.entity.punishment;


import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class PunishmentHistory {

    @Id
    private UUID player;

    private List<Punishment> punishmentList = new ArrayList<>();

}
