package fr.kodeur.tasksmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.kodeur.tasksmanagement.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	public Task findByTitle(String title);
}
