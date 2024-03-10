package lc.mine.api.controller;

import lc.mine.api.entity.punishment.Punishment;
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

    @PostMapping("/v1/punishment")
    public ResponseEntity<Punishment> savePunishment(@RequestBody Punishment punishment) {
        return ResponseEntity.ok(punishmentService.savePunishment(punishment));
    }
    @PostMapping("/v1/punishments")
    public ResponseEntity<List<Punishment>> savePunishment(@RequestBody List<Punishment> punishment) {
        return ResponseEntity.ok(punishmentService.savePunishmentList(punishment));
    }

    @GetMapping("/v1/punishments/player/{playerId}")
    public ResponseEntity<List<Punishment>> findAllPunishmentPlayer(@PathVariable UUID playerId, @RequestParam(required = false) boolean active) {
        return ResponseEntity.ok((active) ? punishmentService.findActivePunishmentsForPlayer(playerId) : punishmentService.findByPlayer(playerId));
    }

    @GetMapping("/v1/punishments")
    public ResponseEntity<List<Punishment>> findActivePunishments(@RequestParam(required = false) boolean active) {
        return ResponseEntity.ok((active) ? punishmentService.findActivePunishments() : punishmentService.findAll());

    }

    @GetMapping("/v1/punishments/ip/{ip}")
    public ResponseEntity<List<Punishment>> findAllPunishmentPlayer(@PathVariable String ip, @RequestParam(required = false) boolean active) {
        return ResponseEntity.ok((active) ? punishmentService.findActivePunishmentForIP(ip) : punishmentService.findByIp(ip));
    }

}
