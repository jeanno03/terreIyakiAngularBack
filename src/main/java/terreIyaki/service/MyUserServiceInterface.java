package terreIyaki.service;

import terreIyaki.entity.MyUser;
import terreIyaki.entity.TheMessage;

public interface MyUserServiceInterface {
	
	public MyUser createMyUser(MyUser myUser02);
	
	public TheMessage createMyUserMessage(MyUser myUser01);
	
}
