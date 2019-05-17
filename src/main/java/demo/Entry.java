package demo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyTarget{}

// define annotation "MyAnnotation"
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
  String hello() default "gege";
  // if no default, then must be define
  String world();

  int[] array() default {2,4,5,6};
  Class style() default String.class;
}


@MyAnnotation(hello="beijing",world="shanghai",array={1,3})
class MyTest {
  @MyAnnotation(world="wrd",array={5,7})
  public void output(){
    System.out.println("outptu sth");
  }
}


public class Entry {
  @MyTarget
  public void doSth(){
    System.out.println("hello world");
  }

  public static void main(String[] args) throws Exception {
    Method method = Entry.class.getMethod("doSth", null);
    Method m = MyTest.class.getMethod("output", new Class[]{});
    if (MyTest.class.isAnnotationPresent(MyAnnotation.class)){
      System.out.println("have annotation");
    }
    
    if(method.isAnnotationPresent(MyTarget.class)){
      System.out.println(method.getAnnotation(MyTarget.class));
    }

    if (m.isAnnotationPresent(MyAnnotation.class)){
      MyAnnotation myAnnotation = m.getAnnotation(MyAnnotation.class);
      String hello = myAnnotation.hello();
      String world = myAnnotation.world();
      System.out.println(hello+","+world);

      MyAnnotation a1 = MyTest.class.getAnnotation(MyAnnotation.class);
      System.out.println(a1.hello()+","+a1.world());
      for(int i: a1.array()){
        System.out.println(i);
      }
    }

  }
}










