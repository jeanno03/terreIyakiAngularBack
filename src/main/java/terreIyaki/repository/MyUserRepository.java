package terreIyaki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import terreIyaki.entity.MyUser;
@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
@Repository
//@EnableJpaRepositories
public interface MyUserRepository extends JpaRepository<MyUser,Long>{
	
	MyUser getUserByEmail(String email);
	
	MyUser getUserByLogin(String login);
	
	MyUser findUserByEmail(String email, String test);
	
	MyUser findById(Long id);
	
}
