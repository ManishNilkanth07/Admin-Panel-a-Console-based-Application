package Validation;

import java.util.List;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^[6-9][0-9]{9}$");

    // Private constructor to prevent instantiation
    private Validator() {

    }

    public static boolean isValidEmail(String email)
    {
        if(email != null)
        {
          return EMAIL_PATTERN.matcher(email).matches();
        }
        return false;
    }

    public static boolean areValidMobileNumbers(List<String> mobileNumbers)
    {
        if(mobileNumbers != null)
        {
            return mobileNumbers.stream()
                    .allMatch(mobile -> MOBILE_PATTERN.matcher(mobile)
                            .matches());
        }
        return false;
    }
}
