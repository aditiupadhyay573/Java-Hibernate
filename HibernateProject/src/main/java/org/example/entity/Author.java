package org.example.entity;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Author")
public class Author {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int authorId;
    private String name;
    private String lastName;


   
    public int getAuthorId() {
        return authorId;
    }
  
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
  
    public String getName() {
        return name;
    }
   
    public void setName(String name) {
        this.name = name;
    }
   
    public String getLastName() {
        return lastName;
    }
   
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



}
