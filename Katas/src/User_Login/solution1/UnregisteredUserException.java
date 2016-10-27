package User_Login.solution1;

class UnregisteredUserException extends Exception {
    UnregisteredUserException() {
        super("New users need to register first");

    }
}
