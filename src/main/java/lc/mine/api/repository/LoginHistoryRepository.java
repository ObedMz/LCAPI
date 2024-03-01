package lc.mine.api.repository;

import lc.mine.api.entity.auth.LoginHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginHistoryRepository extends MongoRepository<LoginHistory, UUID>{


}
