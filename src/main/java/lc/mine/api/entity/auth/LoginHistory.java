package lc.mine.api.entity.auth;


import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;


@Data
public class LoginHistory {

    @Id
    private UUID player;

    private List<Login> logins;
}
