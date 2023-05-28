// package com.salon.salon.models;

// import java.util.Collection;
// import java.util.Set;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Entity
// @Table(name = "users")
// @NoArgsConstructor
// @Getter
// @Setter
// public class User implements UserDetails{
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "id")
//     private int id;
    
//     @Column(name = "urername", unique = true)
//     private String username;

//     @Column(name = "password")
//     private String password;

//     @OneToMany(mappedBy = "user")
//     private Set<Role> roles;

//     @Override
//     public String getUsername() {
//         return username;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }

//     // @Override
//     // public Collection<? extends GrantedAuthority> getAuthorities() {
//     //     return getRole();
//     // }

//     @Override
//     public String getPassword() {
//         return password;
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         // throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
//         return getRoles();
//     }
    
// }
