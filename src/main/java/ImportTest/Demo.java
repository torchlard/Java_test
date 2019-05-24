package ImportTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

interface Car {
  public void print();
}

@Component
class Toyota implements Car {
  public void print(){
    System.out.println("i am toyota");
  }
}

@Component
class Volkswagen implements Car {
  public void print(){
    System.out.println("i ma volkswagen");
  }
}

@Configuration
class JavaConfigA {
  @Bean(name="volkswagen")
  public Car getVolkswagen(){
    return new Volkswagen();
  }
}

@Configuration
class JavaConfigB {
  @Bean(name="toyota")
  public Car getToyota(){
    return new Toyota();
  }
}

@Configuration
@Import({JavaConfigA.class, JavaConfigB.class})
class ParentConfig {

}

public class Demo {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ParentConfig.class);

    Car car = (Toyota)context.getBean("toyota");
    car.print();
    car = (Volkswagen)context.getBean("volkswagen");
    car.print();

    context.close();

  }
}













