package usopshiy.isec1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ACCOUNTS")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    String login;

    @Column
    String password;
}
