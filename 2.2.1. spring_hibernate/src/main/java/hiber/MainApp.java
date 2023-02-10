package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;



//4. Создайте несколько пользователей с машинами,
//добавьте их в базу данных, вытащите обратно.
//5. В сервис добавьте метод,
//который с помощью hql-запроса будет доставать юзера,
//владеющего машиной по ее модели и серии.

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);



      Car car1 = new Car("Model 1", 1);
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
      Car car2 = new Car("Model 2", 2);
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      Car car3 = new Car("Model 3", 3);
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
      Car car4 = new Car("Model 4", 4);
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car{"+user.getCar());
         System.out.println();
      }
      try {
         System.out.println("getByCar");
         System.out.println(users.get(0));
         System.out.println(userService.getByCar("Model 1", 1));
      } finally {
         userService.dropTables();
      }




      context.close();
   }
}
