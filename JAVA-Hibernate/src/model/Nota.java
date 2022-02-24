package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  //Specifies that this corresponts to a Database table.
@Table(name= "Nota") //Specifies the tributes of the table or de schema if needed
public class Nota {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //with this tag we make the id auto_increment
	@Column(name = "id", unique = true, nullable = true) //with this tag we specify the atributes
	private int id;										 //name specify the column name on the Database
														 //unique = true/false specifies if there is a unique constraint
	@Column												 //nullable = treu/false specifies that the values can be null or not
	private int qualificacion;
	
	@Column
	private String abreviatura;
	
	//Empty constructor is needed
	public Nota(){} 
	 
	public Nota(int qualificacion, String abreviatura) {
		this.qualificacion = qualificacion;
		this.abreviatura = abreviatura;
	}

	@Override
	public String toString() {
		return "Nota [id=" + id + ", qualificacion=" + qualificacion
				+ ", abreviatura=" + abreviatura + "]";
	}
	
}
