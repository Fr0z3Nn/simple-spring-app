package ru.svivanov.repository;
import org.springframework.data.repository.CrudRepository;
import ru.svivanov.models.Message;

public interface MessageRepo extends CrudRepository<Message, Long> {
}
