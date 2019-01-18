package fr.kodeur.tasksmanagement.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.kodeur.tasksmanagement.model.util.AbstractPersistent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Task extends AbstractPersistent {
	
	@Column(unique = true)
	private String title;
	
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	private boolean completed;
	
	@OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
	private List<Comment> comments = new ArrayList<>();

	public Task(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
}
