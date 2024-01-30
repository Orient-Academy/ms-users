package az.edu.orient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import az.edu.orient.entity.UserEntity;
import az.edu.orient.exception.UserNotFoundException;
import az.edu.orient.mapper.UserMapper;
import az.edu.orient.model.User;
import az.edu.orient.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<User> getAllUsers() {
     List<UserEntity> users = userRepository.findAll();
     return UserMapper.INSTANCE.toDtos(users);
  }

  public User getUserById(Integer id) {
    UserEntity userEntity =  userRepository.findById(id)
        .orElseThrow( () -> new UserNotFoundException("User By Id " + id + " is not found"));
    return UserMapper.INSTANCE.toDto(userEntity);
  }

  public User save(User user) {
    UserEntity userEntity = UserMapper.INSTANCE.toEntity(user);
    UserEntity saved = userRepository.save(userEntity);
    return UserMapper.INSTANCE.toDto(saved);
  }

  public User update(Integer id, User user) {
    UserEntity userEntity =  userRepository.findById(id)
        .orElseThrow( () -> new UserNotFoundException("User By Id " + id + " is not found"));
    UserMapper.INSTANCE.update(user, userEntity);
    UserEntity saved = userRepository.save(userEntity);
    return UserMapper.INSTANCE.toDto(saved);
  }

  public void deleteUserById(Integer id) {
    userRepository.deleteById(id);
  }



}
