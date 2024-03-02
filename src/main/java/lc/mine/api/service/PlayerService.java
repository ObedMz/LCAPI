package lc.mine.api.service;


import lc.mine.api.entity.Player;
import lc.mine.api.entity.rank.RankInfo;
import lc.mine.api.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {


    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private RankService rankService;


    public Player savePlayer(Player player) {
        if (player.getUuid() == null) {
            player.setUuid(UUID.randomUUID());
            RankInfo rankInfo = new RankInfo();
            rankInfo.setRank(rankService.getDefaultRank());

            player.setRankInfo(rankInfo);
        }
        if (findByUsername(player.getUsername()).isPresent()) {
            return null;
        }
        return playerRepository.save(player);
    }

    public Player findById(UUID id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Optional<Player> findByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    public void deletePlayer(UUID id) {
        playerRepository.deleteById(id);
    }
}
