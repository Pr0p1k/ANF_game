package Repositories;

import EntityClasses.PrivateMessage;
import EntityClasses.MessageCompositeKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for message entity
 */
@Repository
public interface MessagesRepository extends CrudRepository<PrivateMessage, MessageCompositeKey> {
    @Query("update PrivateMessage p set isRead = true where p.message_id.receiver = :receiver AND p.message_id.sender = :sender AND p.message_id.message = :message")
    void setRead(@Param("sender") String senderName, @Param("receiver") String receiverName, @Param("message") String messageText);
}
