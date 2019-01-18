package fr.kodeur.tasksmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.kodeur.tasksmanagement.dao.TaskRepository;
import fr.kodeur.tasksmanagement.model.Comment;
import fr.kodeur.tasksmanagement.model.Task;
import fr.kodeur.tasksmanagement.service.CommentService;
import fr.kodeur.tasksmanagement.service.TaskService;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private CommentService commentService;
	
	@Override
	public Task getTaskById(Long id) {
		Task task = taskRepository.getOne(id);
		if(task == null) throw new RuntimeException("Task not exist !");
		return task;
	}

	@Override
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task saveTask(Task task) {
		if(getTaskByTitle(task.getTitle()) != null)
			throw new RuntimeException("Task already exist !");
		return taskRepository.save(task);
	}

	@Override
	public Task updateTask(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public void deleteTask(Long id) {
		Task task = getTaskById(id);
		taskRepository.delete(task);
	}

	@Override
	public Task getTaskByTitle(String title) {
		return taskRepository.findByTitle(title);
	}

	@Override
	public Task addCommentToTask(String content, Long idTask) {
		Comment comment = new Comment(content);
		Task task = getTaskById(idTask);
		comment.setTask(task);
		commentService.saveComment(comment);
		task.getComments().add(comment);
		return task;
	}


}
