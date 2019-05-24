package demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.AliasFor;

@Retention(RetentionPolicy.RUNTIME)
@interface MyTarget{}

// define annotation "MyAnnotation"
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
  @AliasFor("world")
  String hello() default "gege";
  // if no default, then must be define
  @AliasFor("hello")
  String world() default "gege";

  int[] array() default {2,4,5,6};
  Class style() default String.class;
}


@MyAnnotation(hello="paper", array={5,7})
class MyTest {
  // @MyAnnotation(hello="beijing", array={1,3})
  public void output(){
    System.out.println("outptu sth");
  }
}


// public class Entry {
//   @MyTarget
//   public void doSth(){
//     System.out.println("hello world");
//   }

//   public static void main(String[] args) throws Exception {
//     // Method method = demo.Entry.class.getMethod("doSth", null);
//     Method m = MyTest.class.getMethod("output", new Class[]{});

//     // if(method.isAnnotationPresent(MyTarget.class)){
//     //   System.out.println(method.getAnnotation(MyTarget.class));
//     // }
//     // if (MyTest.class.isAnnotationPresent(MyAnnotation.class)){
//     //   System.out.println("have annotation");
//     // }
    
//     MyAnnotation a1 = MyTest.class.getAnnotation(MyAnnotation.class);
//     System.out.println(a1.hello()+","+a1.world());


//     if (m.isAnnotationPresent(MyAnnotation.class)){
//       // MyAnnotation myAnnotation = m.getAnnotation(MyAnnotation.class);
//       // String hello = myAnnotation.hello();
//       // String world = myAnnotation.world();
//       // System.out.println(hello+","+world);

//       // MyAnnotation a1 = MyTest.class.getAnnotation(MyAnnotation.class);
//       // System.out.println(a1.hello()+","+a1.world());
//       // for(int i: a1.array()){
//       //   System.out.println(i);
//       // }
//     }

//   }
// }



@SpringBootApplication
public class Entry implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(Entry.class, args);
  }


  @Override
  public void run(String... args0) throws Exception {
    
    MyAnnotation a1 = MyTest.class.getAnnotation(MyAnnotation.class);
    System.out.println(a1.hello()+","+a1.world());

  }
  
}  







