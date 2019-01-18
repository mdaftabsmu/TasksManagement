package fr.kodeur.tasksmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.kodeur.tasksmanagement.dao.CommentRepository;
import fr.kodeur.tasksmanagement.model.Comment;
import fr.kodeur.tasksmanagement.model.Task;
import fr.kodeur.tasksmanagement.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public Comment getCommentById(Long id) {
		Comment comment = commentRepository.getOne(id);
		if(comment == null) throw new RuntimeException("Comment not exist !");
		return comment;
	}

	@Override
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}

	@Override
	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public Comment updateComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public void deleteComment(Long id) {
		Comment comment = getCommentById(id);
		commentRepository.delete(comment);
	}

	@Override
	public List<Comment> getCommentsByTask(Task task) {
		return commentRepository.findByTask(task);
	}

}
