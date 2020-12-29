package net.roburterra.guestbook.dao;

import net.roburterra.guestbook.domain.GuestbookMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GuestbookMessageRepository extends CrudRepository<GuestbookMessage,Long> {
    List<GuestbookMessage> findAll();
}
