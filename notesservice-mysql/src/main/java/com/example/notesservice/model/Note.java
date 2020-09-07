package com.example.notesservice.model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="notes")
@Data
@NoArgsConstructor
public class Note {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String title;
	@Column
	private String author;
	@Column
	private String description;
	@Column
	private String status;
	
}
