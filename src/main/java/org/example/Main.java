package org.example;

public class Main {
    public static void main(String[] args) {
        UserManagement userManagement = new UserManagement();

        userManagement.registerUser("hannah", "helloworld", UserRole.STUDENT);
        userManagement.registerUser("faruk", "ilovejava", UserRole.TEACHER);
        userManagement.registerUser("mozamel", "startsteps", UserRole.ADMIN);

        //successful login
        System.out.println(userManagement.loginUser("hannah", "helloworld", UserRole.STUDENT));
        //unsuccessful login
        System.out.println(userManagement.loginUser("faruk", "helloworld", UserRole.TEACHER));
    }
}