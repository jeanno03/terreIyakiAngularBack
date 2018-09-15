package terreIyaki.service;

import org.springframework.web.bind.annotation.RequestBody;

import terreIyaki.entity.Comment;
import terreIyaki.entity.MyUser;
import terreIyaki.entity.TheMessage;
import terreIyaki.tool.ComentTool;

public interface MyUserServiceInterface {

	public MyUser createMyUser(MyUser myUser02);

	public TheMessage createMyUserMessage(MyUser myUser01);

	public TheMessage editMyUserMessage(MyUser myUser01);
	
	public TheMessage insertComment(String userId,ComentTool comment);
	

}
