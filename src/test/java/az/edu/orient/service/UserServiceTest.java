package az.edu.orient.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import az.edu.orient.entity.UserEntity;
import az.edu.orient.exception.UserNotFoundException;
import az.edu.orient.model.User;
import az.edu.orient.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @InjectMocks
  private UserService userService;
  @Mock
  private UserRepository userRepository;

  private final static int USER_ID = 5;
  private final UserEntity USER_ENTITY = new UserEntity(USER_ID, "Rehman", "Baxisli");

  @Test
  void getAllUsersGivenValidEntityThenDto() {
    //Setup
    Mockito.when(userRepository.findAll()).thenReturn(List.of(USER_ENTITY));
    //When
    List<User> users = userService.getAllUsers();
    //Expect
    assertFalse(users.isEmpty());
  }

  @Test
  void deleteUserByIdGivenValidEntityIdReturnNothing() {
    //Setup
    Mockito.doNothing().when(userRepository).deleteById(ArgumentMatchers.anyInt());
    //When
    userService.deleteUserById(USER_ID);
    //Expect
    Mockito.verify(userRepository).deleteById(USER_ID);
  }

  @Test
  void getUserByIdGivenValidIdReturnDto() {
    //Setup
    Mockito.when(userRepository.findById(USER_ID)).thenReturn(Optional.of(USER_ENTITY));
    //when
    User user = userService.getUserById(USER_ID);
    //expect
    assertEquals(user.getId(), USER_ENTITY.getId());
    assertEquals(user.getFirstName(), USER_ENTITY.getFirstName());
    assertEquals(user.getLastName(), USER_ENTITY.getLastName());
  }

  @Test
  void getUserByIdGivenInValidIdReturnUserNotFoundException() {
    //Setup
    Mockito.when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());
    //when
    Exception exception = assertThrows(UserNotFoundException.class, () -> userService.getUserById(USER_ID));
    //expect
    assertEquals(exception.getMessage(), "User By Id "+USER_ID+" is not found");
  }


}