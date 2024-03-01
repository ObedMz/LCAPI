package lc.mine.api.repository;

import lc.mine.api.entity.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends MongoRepository<Player, UUID> {
    Optional<Player> findByUsername(String username);
}
