package lc.mine.api.service;


import lc.mine.api.entity.punishment.Punishment;
import lc.mine.api.entity.punishment.PunishmentHistory;
import lc.mine.api.repository.PunishmentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
    public List<Punishment> savePunishmentList(List<Punishment> punishment, UUID playerId) {
        PunishmentHistory punishmentHistory = punishmentHistoryRepository.findByPlayer(playerId);
        if (punishmentHistory == null) {
            punishmentHistory = new PunishmentHistory();
            punishmentHistory.setPlayer(playerId);
        }
        punishmentHistory.setPunishmentList(punishment);
        return punishmentHistoryRepository.save(punishmentHistory).getPunishmentList();

    }


    public PunishmentHistory findByPlayer(UUID uuid) {
        return punishmentHistoryRepository.findByPlayer(uuid);
    }

    public PunishmentHistory findActivePunishmentsForPlayer(UUID playerId) {
        return punishmentHistoryRepository.findActivePunishmentsForPlayer(playerId);
    }

    public List<PunishmentHistory> findActivePunishments() {
        List<PunishmentHistory> list = punishmentHistoryRepository.findAll();
        for (PunishmentHistory punishmentHistory : list)
            punishmentHistory.getPunishmentList().removeIf(item -> !item.getActive());
        return list;
    }

    public List<PunishmentHistory> findAll() {
        return punishmentHistoryRepository.findAll();
    }

    public List<PunishmentHistory> findAllPunishmentsForIP(String ip) {
        List<PunishmentHistory> punishmentHistories = new ArrayList<>();
        for(PunishmentHistory punishmentHistory : findAll()){
            for(Punishment punishment : punishmentHistory.getPunishmentList()){
                if(punishment.getIp().equals(ip)){
                    punishmentHistories.add(punishmentHistory);
                    break;
                }
            }
        }
        return punishmentHistories;
    }

    public Punishment findActivePunishmentForIP(String ip) {
        List<PunishmentHistory> allPunish = findAllPunishmentsForIP(ip);

        for (PunishmentHistory punish : allPunish) {
            for (Punishment punishment : punish.getPunishmentList()) {
                    if(!punishment.getActive()){
                        System.out.println("saltando punishment");
                        continue;
                    }
                    if(!isExpired(punishment.getExpiresInstant())){
                        System.out.println("retornando" + punishment);

                        return punishment;
                    }
            }

        }
        return null;
    }
    private Boolean isExpired(Instant time){
        Instant currentInstant = Instant.now();
        if(time == null)
            return false;
        return currentInstant.isAfter(time);

    }
}
