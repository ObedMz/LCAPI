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

    @GetMapping("/v1/punishment/ip/history")
    public ResponseEntity<List<PunishmentHistory>> findActivePunishments(@RequestParam String ip) {
        return ResponseEntity.ok(punishmentService.findActivePunishmentsByIP(ip));
    }

    @GetMapping("/v1/punishment/{playerId}/history")
    public ResponseEntity<PunishmentHistory> findActivePunishmentsForPlayer(@PathVariable UUID playerId, @RequestParam(required = false) boolean active) {
        if (active) {
            return ResponseEntity.ok(punishmentService.findActivePunishmentsForPlayer(playerId));
        } else {
            return ResponseEntity.ok(punishmentService.findByPlayer(playerId));
        }
    }

    @GetMapping("/v1/punishment/history/all")
    public ResponseEntity<List<PunishmentHistory>> findActivePunishments() {
        return ResponseEntity.ok(punishmentService.findActivePunishments());
    }

}
