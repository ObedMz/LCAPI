package lc.mine.api.repository;

import lc.mine.api.entity.rank.Rank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RankRepository extends MongoRepository<Rank, UUID>{

    Rank findByName(String name);

    Rank findRankByDefaultRank(boolean defaultRank);

}
