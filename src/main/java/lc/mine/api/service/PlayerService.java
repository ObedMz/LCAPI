package lc.mine.api.service;


import lc.mine.api.entity.Player;
import lc.mine.api.entity.punishment.Punishment;
import lc.mine.api.entity.rank.RankInfo;
import lc.mine.api.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class PlayerService {


    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private RankService rankService;

    @Autowired
    private PunishmentService punishmentService;


    public Player savePlayer(Player player) {
        if (player.getUuid() == null) {
            player.setUuid(UUID.randomUUID());
            RankInfo rankInfo = new RankInfo();
            rankInfo.setRank(rankService.getDefaultRank());
            rankInfo.setHide(false);
            rankInfo.setPermanent(true);
            rankInfo.setUserColor("&7");
            player.setRankInfo(rankInfo);
        }
        return playerRepository.save(player);
    }
    public Slice<Player> getAllPlayers(Integer amount) {
        Pageable pageable = PageRequest.of(0, amount != null ? amount : Integer.MAX_VALUE);
        return playerRepository.findAll(pageable);
    }
    public Player findById(UUID id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Optional<Player> findByUsername(String username) {
        Optional<Player> p = playerRepository.findByUsername(username);
        p.ifPresent(player -> player.setPunishmentList(punishmentService.findActivePunishmentsForPlayer(player.getUuid())));
        return p;
    }

    public void deletePlayer(UUID id) {
        playerRepository.deleteById(id);
    }
}
