package lc.mine.api.repository;

import lc.mine.api.entity.punishment.Punishment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PunishmentHistoryRepository extends MongoRepository<Punishment, UUID> {
    @Query("{ 'active' : true }")
    List<Punishment> findActivePunishments();

    @Query("{ 'player' : ?0, 'active' : true }")
    List<Punishment>  findActiveByPlayer(UUID playerId);

    @Query("{ 'ip' : ?0, 'active' : true }")
    List<Punishment>  findActiveByIP(String ip);

    @Query("{ 'ip' : ?0}")
    List<Punishment> findByIp(String ip);

    @Query("{ 'player' : ?0}")
    List<Punishment> findByPlayer(UUID id);
}