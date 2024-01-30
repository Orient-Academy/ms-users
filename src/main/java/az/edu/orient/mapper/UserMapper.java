package az.edu.orient.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import az.edu.orient.entity.UserEntity;
import az.edu.orient.model.User;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  User toDto(UserEntity userEntity);

  List<User> toDtos(List<UserEntity> userEntity);

  @Mapping(target = "id", ignore = true)
  UserEntity toEntity(User user);

  @Mapping(target = "id", ignore = true)
  void update(User user, @MappingTarget UserEntity userEntity);
}
