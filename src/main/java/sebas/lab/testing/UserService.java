package sebas.lab.testing;

public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public String getUserName(String id) {
        User user = repository.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return user.getName();
    }
}
