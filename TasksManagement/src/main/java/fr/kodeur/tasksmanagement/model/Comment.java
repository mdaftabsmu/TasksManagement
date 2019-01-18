package fr.kodeur.tasksmanagement.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.kodeur.tasksmanagement.model.util.AbstractPersistent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(callSuper = true)
public class Comment extends AbstractPersistent {
	
	private String comment;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Task task;

	public Comment(String comment) {
		super();
		this.comment = comment;
	}
}
