package terreIyaki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import terreIyaki.entity.Comment;

@RepositoryRestResource
@CrossOrigin(origins = "*")
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

//	@Query("select count(*) from Comment")
//	long getCount();
	
}
