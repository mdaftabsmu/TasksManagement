package fr.kodeur.tasksmanagement;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.kodeur.tasksmanagement.model.Task;
import fr.kodeur.tasksmanagement.service.CommentService;
import fr.kodeur.tasksmanagement.service.TaskService;

@SpringBootApplication
public class TasksManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksManagementApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(TaskService taskService, CommentService commentService) {
		
		return args -> {
			Stream.of("Task 1", "Task 2", "Task 3").forEach(task -> {
				taskService.saveTask(new Task(task, "Description for " + task));
			});
			
			Stream.of("Comment 1", "Comment 2", "Comment 3").forEach(comment -> {
				taskService.addCommentToTask(comment + " for task 1", 1L);
				taskService.addCommentToTask(comment + " for task 2", 2L);
				taskService.addCommentToTask(comment + " for task 3", 3L);
			});
			
			
		};
	}

}
