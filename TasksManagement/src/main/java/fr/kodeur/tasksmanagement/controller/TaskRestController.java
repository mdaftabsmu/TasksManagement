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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.kodeur.tasksmanagement.model.Comment;
import fr.kodeur.tasksmanagement.model.Task;
import fr.kodeur.tasksmanagement.service.CommentService;
import fr.kodeur.tasksmanagement.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping
	public List<Task> tasks(){
		return taskService.getAllTasks();
	}
	
	@GetMapping("/{id}")
	public Task getTask(@PathVariable("id") Long id) {
		return taskService.getTaskById(id);
	}
	
	@PostMapping
	public Task createTask(@RequestBody Task task) {
		return taskService.saveTask(task);
	}
	
	@PutMapping("/addComment/{id}")
	public Task addCommentToTask(@RequestParam String comment, @PathVariable("id") Long idTask) {
		return taskService.addCommentToTask(comment, idTask);
	}
	
	
	@PutMapping
	public Task updateTask(@RequestBody Task task) {
		return taskService.updateTask(task);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable("id") Long id) {
		taskService.deleteTask(id);
	}
	
	@GetMapping("/{id}/comments")
	public List<Comment> getCommentsForTask(@PathVariable("id") Long idTask){
		Task task = taskService.getTaskById(idTask);
		return commentService.getCommentsByTask(task);
	}
}
