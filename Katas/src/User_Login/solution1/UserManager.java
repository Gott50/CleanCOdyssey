package User_Login.solution1;

import java.util.ArrayList;
import java.util.Objects;

class UserManager {
    private final ArrayList<User> users = new ArrayList<>();

    User getUser(String loginName) {
        int userIndex = getUserIndex(loginName);
        return users.get(userIndex);
    }

    ArrayList<User> getUsers() {
        return users;

    }

    private int getUserIndex(String loginName) {
        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(users.get(i).id, loginName) || Objects.equals(users.get(i).email, loginName)
                    || Objects.equals(users.get(i).nickname, loginName))
                return i;
        }
        return -1;
    }

    boolean isUserRegistered(String loginName) {
        for (User user : users)
            if ((Objects.equals(user.email, loginName) || Objects.equals(user.nickname, loginName)))
                return true;
        return false;
    }

    public void remove(User user) {
        users.remove(user);
    }
}
