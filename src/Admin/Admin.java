package Admin;

import User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Admin implements AdminService{

    private final List<User> userList = new ArrayList<>();

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    private static final String MOBILE_REGEX = "^[6-9]\\d{9}$";

    public static boolean isValidEmail(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    public static boolean isValidMobileNumber(List<String> mobileNumbers) {
        return mobileNumbers.stream().allMatch(mobile -> Pattern.compile(MOBILE_REGEX).matcher(mobile).matches());
    }

    @Override
    public void createUser(String name, String email, List<String> mobile) {
        userList.forEach(user -> {
            if(user.getEmail().equals(email))
            {
                System.out.println("Email id is already exists!!");
                return;
            }
        });
        if(isValidEmail(email) && isValidMobileNumber(mobile))
        {
            User newUser = new User(name,email,mobile);
            userList.add(newUser);
        }
        else
        {
            System.out.println("Please check email or mobile number format");
            return;
        }

    }

    @Override
    public void getUserByEmail(String email) {
        boolean flag = false;
         for(User user : userList)
         {
             if(user.getEmail().equals(email))
             {
                 System.out.println("User name :" + user.getName());
                 System.out.println("User email :" + user.getEmail());
                 System.out.println("User mobile numbers : ");
                 user.getMobile().forEach(System.out::println);
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
        userList.forEach(user -> {
            System.out.println("User name :" + user.getName());
            System.out.println("User email :" + user.getEmail());
            System.out.println("User mobile numbers : ");
            user.getMobile().forEach(System.out::println);
        });
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
            boolean flag = false;
            if(user.getEmail().equals(email))
            {
                if(isValidEmail(email) && isValidMobileNumber(mobile))
                {
                    user.setEmail(email);
                    user.setMobile(mobile);
                    user.setName(name);
                }
                else
                {
                    System.out.println("Please check email or mobile number format");
                    return;
                }
                flag = true;
            }
            if(!flag)
            {
                System.out.println("User not found with given email!!");
            }

        }
    }
}
