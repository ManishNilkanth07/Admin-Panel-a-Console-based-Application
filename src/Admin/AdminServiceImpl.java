package Admin;

import User.User;
import Validation.Validator;

import java.util.ArrayList;
import java.util.List;


public class AdminServiceImpl implements AdminService{

    private final List<User> userList = new ArrayList<>();

    @Override
    public void createUser(String name, String email, List<String> mobile) {

        User newUser = new User(name.trim(),email,mobile);
        userList.add(newUser);
        System.out.println("User has been created successfully.");
    }

    @Override
    public void getUserByEmail(String email) {
        boolean flag = false;
         for(User user : userList)
         {
             if(user.getEmail().equals(email))
             {
                 System.out.println("{");
                 System.out.println("\t User name :" + user.getName());
                 System.out.println("\t User email :" + user.getEmail());
                 System.out.print("\t User mobile numbers : ");
                 user.getMobile().forEach(System.out::println);
                 System.out.println("}");
                 flag = true;
                 break;
             }
         }
         if(!flag)
         {
             System.out.println("User not found with given email!!");
         }
    }

    @Override
    public void getAllUsers() {
        System.out.println("[");
        userList.forEach(user -> {
            System.out.println("{");
            System.out.println("\t User name :" + user.getName());
            System.out.println("\t User email :" + user.getEmail());
            System.out.println("\t User mobile numbers : ");
            user.getMobile().forEach(System.out::println);
            System.out.println("},");
        });
        System.out.println("]");
    }

    @Override
    public void deleteUserByEmail(String email) {
        boolean flag = false;
        for(User user : userList)
        {
            if(user.getEmail().equals(email))
            {
                userList.remove(user);
                flag = true;
                System.out.println("User has been deleted successfully with email :"+ email);
                break;
            }
        }
        if(!flag)
        {
            System.out.println("User not found with given email!!");
        }
    }

    @Override
    public void updateUserByEmail(String email,String name, List<String> mobile) {
        for(User user : userList)
        {
            if(user.getEmail().equals(email))
            {
                if(Validator.isValidEmail(email) )
                {
                    if(name != null)
                    {
                        user.setName(name);
                        System.out.println("User name updated successfully!");
                    }
                    if(mobile != null && Validator.areValidMobileNumbers(mobile))
                    {
                        user.setMobile(mobile);
                        System.out.println("User mobile number updated successfully!");
                    }
                }
            }
        }
    }

    @Override
    public boolean isUserPresentByEmail(String email) {
        for(User user : userList)
        {
            if(user.getEmail().equals(email))
                return true;
        }
        return false;
    }
}
