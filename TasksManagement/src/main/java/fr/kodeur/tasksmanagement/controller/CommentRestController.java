package fr.kodeur.tasksmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.kodeur.tasksmanagement.model.Comment;
import fr.kodeur.tasksmanagement.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentRestController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping("/{id}")
	public Comment getComment(@PathVariable("id") Long id) {
		return commentService.getCommentById(id);
	}
	
	@GetMapping
	public List<Comment> getComments(){
		return commentService.getAllComments();
	}
	
	@PostMapping
	public Comment createComment(@RequestBody Comment comment) {
		return commentService.saveComment(comment);
	}
	
	@PutMapping
	public Comment updateComment(@RequestBody Comment comment) {
		return commentService.updateComment(comment);
	}
	
	@DeleteMapping("/{id}")
	public void deleteComment(@PathVariable("id") Long id) {
		commentService.deleteComment(id);
	}
}
