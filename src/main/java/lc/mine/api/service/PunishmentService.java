package lc.mine.api.service;


import lc.mine.api.entity.punishment.Punishment;
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

    public Punishment savePunishment(Punishment punishment) {
        if (punishment.getId() == null)
            punishment.setId(UUID.randomUUID());
        return punishmentHistoryRepository.save(punishment);
    }
    public List<Punishment> savePunishmentList(List<Punishment> punishments) {
        for(Punishment p : punishments)
            punishmentHistoryRepository.save(p);
        return punishments;
    }


    public List<Punishment> findByPlayer(UUID uuid) {
        return punishmentHistoryRepository.findByPlayer(uuid);
    }

    public List<Punishment> findActivePunishmentsForPlayer(UUID playerId) {
        List<Punishment> punishments = new ArrayList<>();
        List<Punishment> to_save = punishmentHistoryRepository.findActiveByPlayer(playerId);

        for(Punishment p : to_save){
            p.setActive(!isExpired(p.getExpiresInstant()));
            if(p.getActive()) punishments.add(p);
        }
        savePunishmentList(to_save);
        return punishments;
    }

    public List<Punishment> findActivePunishments() {
        List<Punishment> punishments = new ArrayList<>();
        List<Punishment> to_save = punishmentHistoryRepository.findActivePunishments();

        for(Punishment p : to_save){
            if(isExpired(p.getExpiresInstant())){
                System.out.println("El rango expiró.");
                p.setActive(false);
                savePunishment(p);
            }
            if(p.getActive()) punishments.add(p);
        }
        return punishments;
    }

    public List<Punishment> findAll() {
        return punishmentHistoryRepository.findAll();
    }

    public List<Punishment> findByIp(String ip) {
        return punishmentHistoryRepository.findByIp(ip);
    }

    public List<Punishment> findActivePunishmentForIP(String ip) {
        List<Punishment> punishments = new ArrayList<>();
        List<Punishment> to_save = punishmentHistoryRepository.findActiveByIP(ip);

        for(Punishment p : to_save){
            p.setActive(!isExpired(p.getExpiresInstant()));
            if(p.getActive()) punishments.add(p);
        }
        savePunishmentList(to_save);
        return punishments;
    }

    public Punishment findLastByPlayer(UUID player){
        Punishment punishment = punishmentHistoryRepository.findActiveByPlayer(player).stream().findFirst().orElse(null);
        if(punishment != null && isExpired(punishment.getExpiresInstant())){
            punishment.setActive(false);
            savePunishment(punishment);
            return null;
        }
        return punishment;
    }

    public Punishment findLastByIP(String ip){
        Punishment punishment = punishmentHistoryRepository.findActiveByIP(ip).stream().findFirst().orElse(null);
        if(punishment != null && isExpired(punishment.getExpiresInstant())){
            punishment.setActive(false);
            savePunishment(punishment);
           return null;
        }
        return punishment;
    }
    private Boolean isExpired(Instant time){
        return time != null && Instant.now().isAfter(time);
    }
}
