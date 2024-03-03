package lc.mine.api.controller;

import lc.mine.api.entity.Player;
import lc.mine.api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PlayerController {


    @Autowired
    private PlayerService playerService;

    @PostMapping("/v1/player")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        return ResponseEntity.ok(playerService.savePlayer(player));
    }
    @GetMapping("/v1/players")
    public ResponseEntity<List<Player>> getAllPlayers(@RequestParam(required = false) Integer amount) {
        return ResponseEntity.ok(playerService.getAllPlayers(amount).getContent());
    }

    @GetMapping("/v1/player/{username}")
    public ResponseEntity<Player> getPlayer(@PathVariable String username) {
        Player player = playerService.findByUsername(username).orElse(null);
        if (player != null) {
            return ResponseEntity.ok(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/v1/player/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable UUID id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok().build();
    }


}
