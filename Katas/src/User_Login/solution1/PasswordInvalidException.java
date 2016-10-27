package User_Login.solution1;

class PasswordInvalidException extends Exception {
    PasswordInvalidException() {
        super("Password is not Valid!");
    }
}
