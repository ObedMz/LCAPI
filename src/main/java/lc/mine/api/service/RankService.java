package lc.mine.api.service;


import lc.mine.api.entity.rank.Rank;
import lc.mine.api.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RankService {

    @Autowired
    private RankRepository rankRepository;

    public Rank saveRank(Rank rank) {
        return rankRepository.save(rank);
    }

    public Rank findByName(String name) {
        return rankRepository.findByName(name);
    }

    public Rank findById(UUID id) {
        return rankRepository.findById(id).orElse(null);
    }

    public void deleteRank(String name) {
        Rank rank = findByName(name);
        rankRepository.delete(rank);
    }
    public List<Rank> findAllRanks() { return rankRepository.findAll();}

    public Rank getDefaultRank() {
        return rankRepository.findRankByDefaultRank(true);
    }
}
