package lc.mine.api.service;


import lc.mine.api.entity.punishment.Punishment;
import lc.mine.api.entity.punishment.PunishmentHistory;
import lc.mine.api.repository.PunishmentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PunishmentService {

    @Autowired
    private PunishmentHistoryRepository punishmentHistoryRepository;

    public Punishment savePunishment(Punishment punishment, UUID playerId) {
        PunishmentHistory punishmentHistory = punishmentHistoryRepository.findByPlayer(playerId);
        if (punishmentHistory == null) {
            punishmentHistory = new PunishmentHistory();
            punishmentHistory.setPlayer(playerId);
        }
        punishmentHistory.getPunishments().add(punishment);
        return punishmentHistoryRepository.save(punishmentHistory).getPunishments().get(punishmentHistory.getPunishments().size() - 1);

    }
    public PunishmentHistory findByPlayer(UUID uuid) {
        return punishmentHistoryRepository.findByPlayer(uuid);
    }

    public PunishmentHistory findActivePunishmentsForPlayer(UUID playerId) {
        return punishmentHistoryRepository.findActivePunishmentsForPlayer(playerId);
    }

    public List<PunishmentHistory> findActivePunishments() {
        return punishmentHistoryRepository.findActivePunishments();
    }

}
