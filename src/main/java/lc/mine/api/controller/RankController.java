package lc.mine.api.controller;


import lc.mine.api.entity.Rank;
import lc.mine.api.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RankController {

    @Autowired
    private RankService rankService;


    @PostMapping("/v1/rank")
    public ResponseEntity<Rank> saveRank(@RequestBody Rank rank) {
        return ResponseEntity.ok(rankService.saveRank(rank));
    }

    @GetMapping("/v1/ranks")
    public ResponseEntity<List<Rank>> getRanks() {
        return ResponseEntity.ok(rankService.findAllRanks());
    }

    @GetMapping("/v1/rank/{name}")
    public ResponseEntity<Rank> saveRank(@PathVariable String name) {
        Rank rank = name.equalsIgnoreCase("default") ? rankService.getDefaultRank() : rankService.findByName(name);
        return rank == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(rank);
    }

    @DeleteMapping("/v1/rank/{name}")
    public ResponseEntity<Void> deleteRank(@PathVariable String name) {
        rankService.deleteRank(name);
        return ResponseEntity.ok().build();
    }

}
