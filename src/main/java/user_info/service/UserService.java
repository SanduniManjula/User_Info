package user_info.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import user_info.model.User;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private List<User> users;
    private RestTemplate restTemplate;

    public UserService() {
        users = new ArrayList<>();
        restTemplate = new RestTemplate();
    }

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL("https://jsonplaceholder.typicode.com/todos");
        users = mapper.readValue(url, new TypeReference<List<User>>(){});
    }
    public Optional<User> getUserDetailsById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }


    public List<User> getTodosByUserId(int userId) {
        String url = "https://jsonplaceholder.typicode.com/todos";
        User[] todos = restTemplate.getForObject(url, User[].class);
        return Arrays.stream(todos)
                .filter(todo -> todo.getUserId() == userId)
                .collect(Collectors.toList());
    }
/*
    public Optional<User> getUserById(int userId) {
        return users.stream().filter(user -> user.getUserId() == userId).findFirst();
    }

 */

}
