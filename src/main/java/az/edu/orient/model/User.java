package az.edu.orient.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
  private int id;
  private String firstName;
  private String lastName;
}
