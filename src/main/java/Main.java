import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserService(new RestTemplate());
        List<User> users = userService.getAllUsers();
        System.out.println(users);
        String request1 = userService.saveUser();
        System.out.println(request1);
        String request2 = userService.updateUser();
        System.out.println(request2);
        String request3 = userService.deleteUser();
        System.out.println(request3);
        String result = request1 + request2 + request3;
        System.out.println(result);




    }
}
