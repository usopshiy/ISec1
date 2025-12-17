package usopshiy.isec1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usopshiy.isec1.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
