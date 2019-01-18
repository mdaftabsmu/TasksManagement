package fr.kodeur.tasksmanagement.service;

import java.util.List;

import fr.kodeur.tasksmanagement.model.Task;

public interface TaskService {

	public Task getTaskById(Long id);
	
	public List<Task> getAllTasks();
	
	public Task saveTask(Task task);
	
	public Task updateTask (Task task);
	
	public void deleteTask(Long id);
	
	public Task getTaskByTitle(String title);
	
	public Task addCommentToTask(String comment, Long idTask);
}
