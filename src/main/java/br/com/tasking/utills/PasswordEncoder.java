package br.com.tasking.utills;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static final BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();

    public static void main (String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("marcelo"));
    }
}
