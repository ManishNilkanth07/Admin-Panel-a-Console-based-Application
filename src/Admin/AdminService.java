package Admin;

import java.util.List;

public interface AdminService {

    public void createUser(String name, String email, List<String> mobile);

    public void getUserByEmail(String email);

    public void getAllUsers();

    public void deleteUserByEmail(String email);

    public void updateUserByEmail(String email, String name, List<String> mobile);

}
