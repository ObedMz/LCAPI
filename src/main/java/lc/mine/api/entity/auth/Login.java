package lc.mine.api.entity.auth;

import lombok.Data;

import java.time.Instant;

@Data
public class Login {

    private String ip;
    private Instant instant;
    private String status;
}
