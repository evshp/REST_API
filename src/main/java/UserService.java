import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public class UserService {

    private static User user1 = new User(3L,"James", "Brown", (byte) 25  );
    private static User user2 = new User(3L,"Thomas", "Shelby", (byte) 25);

    private static HttpHeaders headers = new HttpHeaders();

    private static String sessionId;
    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<User> getAllUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange("http://94.198.50.185:7081/api/users",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
                sessionId = response.getHeaders().getFirst("Set-Cookie");
                headers.add("Cookie", sessionId);
        return response.getBody();
    }


    public String saveUser() {
        HttpEntity<User> request = new HttpEntity<>(user1, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://94.198.50.185:7081/api/users",
                HttpMethod.POST, request, String.class);
        return response.getBody();
    }

    public String updateUser() {
        HttpEntity<User> request = new HttpEntity<>(user2, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://94.198.50.185:7081/api/users",
                HttpMethod.PUT, request, String.class);
        return response.getBody();
    }


    public String deleteUser() {
        HttpEntity<User> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://94.198.50.185:7081/api/users/3",
                HttpMethod.DELETE, request, String.class);
        return response.getBody();
    }


    public static HttpHeaders getHeaders() {
        return headers;
    }
}
