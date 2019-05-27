package DB;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.tool.schema.extract.spi.NameSpaceTablesInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


// @EnableAutoConfiguration
@ComponentScan
@Configuration
@EnableJpaRepositories(repositoryBaseClass = BaseCustomRepo.class)
@SpringBootApplication
public class Entry implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(Entry.class, args);
  }

  
  @Autowired
  TeacherRepository<Teacher2,Integer> repo;
  // TeacherRepo2 repo;

  // public static String genStr(int size){
  //   int left = 97;
  //   int right = 122;
  //   Random rand = new Random();
  //   StringBuilder buffer = new StringBuilder(size);
  //   for (int i=0; i<size; i++){
  //     int randint = left + (int)(rand.nextFloat()*(right-left+1));
  //     buffer.append((char) randint);
  //   }
  //   return buffer.toString();
  // } 

  @Override
  public void run(String... args0) throws Exception {


    repo.findAllByAgeNotIn(20).forEach(System.out::println);
    
    // repo.getxx1(20, "age").forEach(System.out::println);
    // repo.getxx1(20, "age").forEach(System.out::println);

    // repo.findAllBySex("M").forEach(System.out::println);
    // repo.getxx3().forEach(System.out::println);
    // repo.getxx3().forEach(i -> System.out.println(i.toArray()[1] ));
    // repo.getSth2(20).forEach(i -> System.out.println(i.getId()+ i.getName()+", "+ i.getSex() ));

    // int a = Integer.parseInt(repo.countxx1().get("ct").toString());
    // System.out.println( a+3);
    
    // Teacher2 t = repo.objxx1();
    // System.out.println(t);


    // repo.getSth5(20f).forEach(System.out::println);
    // repo.findAllByAgeNotIn(67).forEach(System.out::println);

    // repo.showAll().forEach(System.out::println);


    // repo.objxx2().forEach(System.out::println);

    // Teacher2[] ll = new Teacher2[2];
    // ll[0] = new Teacher2("jo1","M",59.8f, 31, new BigInteger("547"));
    // ll[1] = new Teacher2("jo2","M",59.8f, 31, new BigInteger("547"));
    

    // System.out.println("----");
    // repo.findAllByAgeNotIn(23).forEach(System.out::println);
    
    // System.out.println("count: " +im.getCount());
    // System.out.println("teacher: " +im.getObj1());
    // System.out.println("teacher: " +im.getObj2(2));
    
    // im.getList1().stream().forEach(System.out::println);
    // im.getList2().stream().forEach(System.out::println);
    // System.out.println(im.update1());

    // System.out.println("result:");
    // repo.findAll().stream().forEach(System.out::println);
    // repo.findAllByName("t2").forEach(System.out::println);

    // List<dto> ll = repo.findAllBySex("F");
    // ll.forEach(i -> System.out.println(i.getNumMod()));

    // List<NamesOnly> l2 = repo.findAllBySex("M");
    // l2.forEach(i -> System.out.println(i.getMod()));
  
    // repo.getSth(34).forEach(System.out::println);
    // repo.getSth2(34, "a").forEach(System.out::println);

    // repo.getSth3(1, new Sort(Direction.ASC, "sex","num")).forEach(System.out::println);
    // repo.getSth4(34, PageRequest.of(1,5, Direction.ASC, "sex", "num")).forEach(System.out::println);

    // repo.setAge1(123, 1);
    // System.out.println(repo.runPlus1(3));

    // repo.query1().forEach(System.out::println);
    // System.out.println("----");
    // repo.query2().forEach(i ->System.out.println(i.get("d")));



    // System.out.println("---");
    // repo.findFirst3BySex("M").forEach(System.out::println);

//    repo.abc().forEach(System.out::println);

    // System.out.println(repo.countByAgeNotIn(33,3,78));

    // String[] sex = {"M","F"};
    // List<Teacher2> ll = new ArrayList<>();
    // Random rand = new Random();
    // for(int i=0; i<10; i++){
    //   Teacher2 t1 = new Teacher2( genStr(rand.nextInt(5)+1) ,sex[rand.nextInt(2)], 
    //     rand.nextFloat()*100, rand.nextInt(100), 
    //     new BigDecimal(rand.nextFloat()*100_000));
    //   ll.add(t1);
    // }
    // repo.saveAll(ll);

    // Integer i=1;
    // Teacher2 t = new Teacher2("jk", "M", 12.5f, 10, new BigDecimal(1));
    System.out.println("======");


  }

}




