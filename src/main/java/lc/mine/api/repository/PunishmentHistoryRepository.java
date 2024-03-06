package lc.mine.api.repository;

import lc.mine.api.entity.punishment.PunishmentHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface PunishmentHistoryRepository extends MongoRepository<PunishmentHistory, UUID> {
    @Query("{ 'punishments.active' : true }")
    List<PunishmentHistory> findActivePunishments();

    @Query("{ 'player' : ?0, 'punishments.active' : true }")
    PunishmentHistory findActivePunishmentsForPlayer(UUID playerId);

    @Query("{ 'punishmentList.ip' : ?0 }")
    List<PunishmentHistory> findByIp(String ip);

    PunishmentHistory findByPlayer(UUID uuid);
}