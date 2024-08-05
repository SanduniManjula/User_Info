package user_info.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import user_info.model.User;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> users;

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL("https://jsonplaceholder.typicode.com/todos");
        users = mapper.readValue(url, new TypeReference<List<User>>(){});
    }

    public Optional<User> getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }
}
