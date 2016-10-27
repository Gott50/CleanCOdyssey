package User_Login.solution1;

class UnconfirmedUserException extends Exception {

    UnconfirmedUserException() {
        super("User is not yet confirmed");
    }
}
