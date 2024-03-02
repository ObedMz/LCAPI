package lc.mine.api.entity.rank;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.UUID;

@Data
@Document("ranks")
public class Rank {

    @Id
    private String name;
    private String prefix;
    private String color;
    private Integer priority;
    private Boolean defaultRank;
    private Set<String> permissions;


}
