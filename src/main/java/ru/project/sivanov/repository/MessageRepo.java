package ru.project.sivanov.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.project.sivanov.models.Message;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {
}
