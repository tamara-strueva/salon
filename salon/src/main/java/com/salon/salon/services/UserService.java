// package com.salon.salon.services;

// import java.util.Collections;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.salon.salon.models.Role;
// import com.salon.salon.models.User;
// import com.salon.salon.repositories.UserRepository;

// import jakarta.transaction.Transactional;

// @Service
// @Transactional
// public class UserService implements UserDetailsService {
//     @Autowired
//     private UserRepository userRepository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = userRepository.findByUsername(username);
//         if (user == null) {
//             throw new UsernameNotFoundException("User not found");
//         }
//         return user;
//     }
    
//     public User findUserById(Integer userId) {
//         Optional<User> userFromDb = userRepository.findById(userId);
//         return userFromDb.orElse(new User());
//     }

//     public List<User> getAllUsers() {
//         return userRepository.findAll();
//     }

//     // public boolean saveUser(User user) {
//     //     User userFromDB = userRepository.findByUsername(user.getUsername());
//     //     if (userFromDB != null) {
//     //         return false;
//     //     }
//     //     user.setRoles(Collections.singleton(new Role("ROLE_USER")));
//     //     user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//     //     userRepository.save(user);
//     //     return true;
//     // }

//     public boolean deleteUser(Integer userId) {
//         if (userRepository.findById(userId).isPresent()) {
//             userRepository.deleteById(userId);
//             return true;
//         }
//         return false;
//     }

//     // public List<User> usergtList(Long idMin) {
//     //     return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
//     //             .setParameter("paramId", idMin).getResultList();
//     // }
// }
