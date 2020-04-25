package app.util;


import app.entity.User;

public class Checkings {

    public static boolean checkUser(User user){
        return checkName(user.getFirstName(), user.getLastName(), user.getMiddleName())
                && checkSalary(user.getSalary())
                && checkAge(user.getAge())
                && checkMail(user.getMail());
    }


    // 147$ = 10 964 RUB - min salary in Russia
    private static boolean checkSalary(double sal){
        if (sal >= 147 && sal <= 10_000) return true;
        return false;
    }

    //working possible age
    private static boolean checkAge(int age){
        if (age >= 18 && age <= 65) return true;
        return false;
    }

    public static boolean checkMail(String mail){
        if (mail != null) return mail.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        return false;
    }

    private static boolean checkName(String... names){
        for (String n:names) {
            if (!n.matches("[A-Z]?[a-z]*")) return false;
        }
        return true;
    }
}
