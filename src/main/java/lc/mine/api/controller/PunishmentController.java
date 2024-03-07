package lc.mine.api.controller;

import lc.mine.api.entity.punishment.Punishment;
import lc.mine.api.entity.punishment.PunishmentHistory;
import lc.mine.api.service.PunishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PunishmentController {

    @Autowired
    private PunishmentService punishmentService;

    @PostMapping("/v1/punishment/{playerId}")
    public ResponseEntity<Punishment> savePunishment(@RequestBody Punishment punishment, @PathVariable UUID playerId) {
        return ResponseEntity.ok(punishmentService.savePunishment(punishment, playerId));
    }
    @PostMapping("/v1/punishments/{playerId}")
    public ResponseEntity<List<Punishment>> savePunishment(@RequestBody List<Punishment> punishment, @PathVariable UUID playerId) {
        return ResponseEntity.ok(punishmentService.savePunishmentList(punishment, playerId));
    }

    @GetMapping("/v1/punishment/{playerId}/history")
    public ResponseEntity<PunishmentHistory> findActivePunishmentsForPlayer(@PathVariable UUID playerId, @RequestParam(required = false) boolean active) {
        if (active) {
            return ResponseEntity.ok(punishmentService.findActivePunishmentsForPlayer(playerId));
        } else {
            return ResponseEntity.ok(punishmentService.findByPlayer(playerId));
        }
    }

    @GetMapping("/v1/punishments")
    public ResponseEntity<List<PunishmentHistory>> findAllPunishments() {
        return ResponseEntity.ok(punishmentService.findAll());
    }

    @GetMapping("/v1/punishments/active")
    public ResponseEntity<List<PunishmentHistory>> findActivePunishments() {
        return ResponseEntity.ok(punishmentService.findActivePunishments());
    }

    @GetMapping("/v1/punishments/player/{playerId}")
    public ResponseEntity<PunishmentHistory> getAllPunishmentByPlayer(@PathVariable UUID playerId) {
        return ResponseEntity.ok(punishmentService.findByPlayer(playerId));
    }

    @GetMapping("/v1/punishments/player/{playerId}/active")
    public ResponseEntity<PunishmentHistory> getActivePunishmentByPlayer(@PathVariable UUID playerId) {
        return ResponseEntity.ok(punishmentService.findActivePunishmentsForPlayer(playerId));
    }

    @GetMapping("/v1/punishments/ip/{ip}")
    public ResponseEntity<List<PunishmentHistory>> getAllPunishmentByIP(@PathVariable String ip) {
        return ResponseEntity.ok(punishmentService.findAllPunishmentsForIP(ip));
    }

    @GetMapping("/v1/punishments/ip/{ip}/active")
    public ResponseEntity<Punishment> getActivePunishmentByIP(@PathVariable String ip) {
        return ResponseEntity.ok(punishmentService.findActivePunishmentForIP(ip));
    }


}
