package hello;
import static java.lang.System.out;
import java.util.Scanner;

public class Main19 {
  public static void main(String args[]){

    CPersonAccount obj = new CPersonAccount();
    char runFunc = 'd';
    Scanner keyboardInput = new Scanner(System.in);

    while(runFunc!='q')
    {
      System.out.print("請選擇作業");
      System.out.print("(i=輸入資料 s=依月薪資排序 d=顯示資料 q=離開):");
      runFunc = keyboardInput.nextLine().charAt(0);
      switch (runFunc)
      {
        case 'i':
           obj.inputData();
           break;
        // case 's':
        //    obj.sortBySalary();
        //    break;
        case 'd':
           obj.displayData();
           break;
       default:
           break;
      }
    }

  }
}

class CPersonAccount {
  private static int index;
  CPersonData data;

  class CPersonData {
    public String name;
    public String degree;
    // private String p_id;
    private int salary;

    public CPersonData(){
      name="~";
      degree="~";
      p_id="~";
      salary=0;
    }
    public CPersonData(String name,String degree,String p_id,int salary){
      // this is necessary in inner class !!!!
      this.name=name;
      this.degree=degree;
      this.p_id=p_id;
      this.salary=salary;
    }
    public void printData(){
      out.println(name+"\t"+degree+"\t"+salary);
    }
    public int getSalary(){
      return salary;
    }
  }

  CPersonAccount(){
    index = 0;
  }

  Scanner keyboardInput = new Scanner(System.in);

  public CPersonData personArr[] = new CPersonData[5];
  public void inputData(){
    out.print("name: ");
    String name = keyboardInput.nextLine();
    out.print("degree: ");
    String degree = keyboardInput.nextLine();
    out.print("p_id: ");
    String p_id = keyboardInput.nextLine();
    out.print("salary: ");
    int salary = Integer.parseInt(keyboardInput.nextLine());

    personArr[index] = new CPersonData(name,degree, p_id, salary);
    index++;
  }
  public void displayData(){
    out.println("name \t position \t salary");
    out.println("------------------------------");
    for (int i=0; i<3; i++){
      personArr[i].printData();
    }
  }

}





