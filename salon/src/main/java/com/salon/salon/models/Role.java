// package com.salon.salon.models;

// import org.springframework.security.core.GrantedAuthority;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;
// import lombok.Getter;
// import lombok.Setter;

// @Entity
// @Table(name = "roles")
// @Getter
// @Setter
// public class Role implements GrantedAuthority {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private int id;

//     private String name;

//     @ManyToOne
//     private User user;

//     @Override
//     public String getAuthority() {
//         return getName();
//     }
    
//     Role (String name) {
//         this.name = name;
//     }
// }
