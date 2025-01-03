import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

public Scanner sc = new Scanner(System.in);
public Time time;
public Menu m;
@SuppressWarnings("FieldMayBeFinal")
private int[] memory = new int[3];
private String name;
private int next_int;

    public static void main(String[] args) {
        System.out.println("class schedule v2.0 \nmade by Zirui Xu and his team");
        new Main();
    }

    public Main() {
        line();
        name();
    }

    public Main(boolean ignoredAbc){
    }

    public Main(String name) {
        this.name = name;
    }


    private void setTime(){
        System.out.println("Firstly please input the amount of your morning classes");
        while (memory[0] == 0){
            int_input();
            memory[0] =Checkint(next_int, 1,9);
        }
        System.out.println("Good job.Secondly please input the amount of your afternoon classes");
        while (memory[1] == 0){
            int_input();
            memory[1] =Checkint(next_int, 1,9);
        }
        System.out.println("Good job.Thirdly please input the amount of your evening classes");
        while (memory[2] == 0){
            int_input();
            memory[2] =Checkint(next_int, 1,9);
        }
        time = new Time(memory[0],memory[1],memory[2],String.copyValueOf(name.toCharArray()),0);
        this.name = null;
        time.print_time(0);
    }



    private void name(){
        System.out.println("Enter your name:");
        name = CheckString(sc.nextLine(), 2);
        if (name != null) {
            System.out.println(name + " is that your name? Y/N");
            Y_N(2);
        }else {
            System.out.println("Illegal name,please try again.");
            name();
        }
    }

    //check area
    public int Checkint(int amount,int min,int max){
        if (amount <= max && amount >= min){
            return amount;
        }else{
            System.out.println("wrong number,please try again.");
            return 0;
        }
    }

    public String CheckString(String input,int type){
        if (type == 1) {
            if (input.trim().length() == 1 && input.startsWith("Y") || input.startsWith("N")) {
                return input.trim();
            } else if (input.isBlank()) {
                throw new RuntimeException("blank string");
            } else {
                //System.out.println("!"+input);
                throw new RuntimeException("invalid string");
            }
        }else if(type == 2){
            if(input.isBlank()){
                return null;
            }else{
                return input;
            }
        }else{
            //System.out.println("error.");
            return null;
        }
    }

    public void Y_N(int type){
        try {
            String YN = sc.nextLine();
            if(CheckString(YN, 1).equals("Y")){
                switch (type){
                    case 1:
                        time = new Time(String.copyValueOf(name.toCharArray()),0);
                        this.name = null;
                        time.print_time(0);
                        break;
                        case 2:
                            m = new Menu(String.copyValueOf(name.toCharArray()));
                            break;
                }
            } else if (CheckString(YN, 1).equals("N")) {
                switch (type){
                    case 1:
                        System.out.println("Ok,let's reset your class amount.");
                        setTime();
                        break;
                        case 2:
                            name();
                            break;
                }
            }
        }catch (RuntimeException e){
            if(e.getMessage()!=null) {
                if (e.getMessage().equals("invalid string")) {
                    System.out.println("Illegal answer,please try again.");
                    Y_N(type);
                } else if (e.getMessage().equals("blank string")) {
                    Y_N(type);
                } else {
                    System.out.println(e.getMessage());
                }
            }else{
                System.out.println("unknown error");
            }
        }
    }


    //useful tool
    private void int_input(){
        sc_clean();
        try {
            next_int = sc.nextInt();
        } catch (InputMismatchException _) {
        }
    }

    public void sc_clean(){
        sc = new Scanner(System.in);
    }

    public void line(){
        System.out.println("----------------------------------------------");
    }

}

