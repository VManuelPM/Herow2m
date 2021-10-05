package com.w2m.service;

import com.w2m.entity.UserEntity;
import com.w2m.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.util.Collections.emptyList;

@Service
@Transactional
public class UserService implements UserDetailsService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    return new User(user.getUsername(), user.getPassword(), emptyList());
  }
}
