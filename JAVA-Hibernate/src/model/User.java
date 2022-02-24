package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity  //Specifies that this corresponts to a Database table.
@Table(name= "User") //Specifies the tributes of the table or de schema if needed
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //with this tag we make the id auto_increment
	@Column(name = "id", unique = true, nullable = true) //with this tag we specify the atributes
	private int id;										 //name specify the column name on the Database
														 //unique = true/false specifies if there is a unique constraint
	@Column												 //nullable = treu/false specifies that the values can be null or not
	private String name;
	
	@Column
	private String sureName;
	
	@Column
	private Date createDate;
	
	@OneToMany //This especifies that for every instance os this class we hav emany of the other
    @JoinColumn(name = "user_id")
	private List<Nota> notas;
	
	@OneToOne //This especifies that has an agregation of another class (table)
	@JoinColumn(name = "nota_media")
	private Nota notaMedia;
	
	//Empty constructor is needed
	public User(){} 
	 
	public User(String name, String sureName) {
		this.name = name;
		this.sureName = sureName;
		this.createDate = new Date();
		this.notas = new ArrayList<>();
	}
	
	public void setSureName(String sureName) {
		this.sureName = sureName;
	}

	public void setNotaMedia(Nota notaMedia) {
		this.notaMedia = notaMedia;
	}
	
	public List<Nota> getNotas() {
		return this.notas;
	}
	
	public Nota getNotaMedia() {
		return this.notaMedia;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sureName=" + sureName
				+ ", createDate=" + createDate + ", notas=" + notas + ", notas=" + notaMedia + "]";
	}

}
