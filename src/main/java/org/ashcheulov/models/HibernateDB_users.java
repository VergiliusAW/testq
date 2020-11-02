package org.ashcheulov.models;

import org.hibernate.annotations.Generated;

import javax.persistence.*;

//@Entity
//@Table(name = "users")
//@Cacheable
//public class HibernateDB {
//
//    @Id
//    @SequenceGenerator(name = "fruitsSequence", sequenceName = "known_fruits_id_seq", allocationSize = 1, initialValue = 10)
//    @GeneratedValue(generator = "fruitsSequence")
//    private Integer id;
//
//    @Column(length = 40, unique = true)
//    private String name;
//
//    public HibernateDB() {
//    }
//
//    public HibernateDB(String name) {
//        this.name = name;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//}

@Entity
@Table(name = "users")
public class HibernateDB_users {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @Column(name = "email")
    private String email;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}