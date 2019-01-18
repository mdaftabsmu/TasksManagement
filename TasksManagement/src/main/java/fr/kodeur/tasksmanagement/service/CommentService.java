package fr.kodeur.tasksmanagement.service;

import java.util.List;

import fr.kodeur.tasksmanagement.model.Comment;
import fr.kodeur.tasksmanagement.model.Task;

public interface CommentService {

	public Comment getCommentById(Long id);
	
	public List<Comment> getAllComments();
	
	public Comment saveComment(Comment comment);
	
	public Comment updateComment(Comment comment);
	
	public void deleteComment(Long id);
	
	public List<Comment> getCommentsByTask(Task task);
}
