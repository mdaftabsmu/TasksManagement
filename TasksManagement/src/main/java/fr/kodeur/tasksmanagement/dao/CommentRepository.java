package fr.kodeur.tasksmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.kodeur.tasksmanagement.model.Comment;
import fr.kodeur.tasksmanagement.model.Task;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	public List<Comment> findByTask(Task task);
}
