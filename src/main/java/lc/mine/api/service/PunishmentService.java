package lc.mine.api.service;


import lc.mine.api.entity.punishment.Punishment;
import lc.mine.api.entity.punishment.PunishmentHistory;
import lc.mine.api.repository.PunishmentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        punishmentHistory.getPunishmentList().add(punishment);
        return punishmentHistoryRepository.save(punishmentHistory).getPunishmentList().get(punishmentHistory.getPunishmentList().size() - 1);

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

    public List<Punishment> findActivePunishmentsByIP(String ip) {
        List<Punishment> punishments = new ArrayList<>();
        punishmentHistoryRepository.findByIp(ip).forEach(l -> {
            l.getPunishmentList().forEach(p -> {
                if(p.getIsIP() && p.getActive())
                    punishments.add(p);
            });
        });
        return punishments;
    }
}
