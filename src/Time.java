import java.util.HashMap;
import java.util.InputMismatchException;

public class Time {
    protected int[][] time;
    protected String [][] course;
    protected static int[] period = new int[3];
    protected HashMap<Integer,String> NameID = new HashMap<>();
    private morning m;
    private afternoon f;
    private evening e;
    private final Main main = new Main(true);
    private int memory;


    public Time(int amount1,int amount2,int amount3,String name,int ID) {
        period[0] = amount1;
        period[1] = amount2;
        period[2] = amount3;
        NameID.put(ID,name);
        m = new morning();
        f = new afternoon();
        e = new evening();
    }


    public void print_time(int id){
            System.out.println(NameID.get(id) + "'s course time:");
            System.out.println("morning class amounts: " + period[0]);
            System.out.println("afternoon class amounts: " + period[1]);
            System.out.println("evening class amounts: " + period[2]);
    }

    public Time() {
    }

    public Time(String name,int ID){
        NameID.put(ID,name);
        period = new int[]{2, 2, 1};
        m = new morning(new int[][]{{8,0,9,40},{10,10,11,50}});
        f = new afternoon(new int[][]{{13,45,15,25},{15,55,17,35}});
        e = new evening(new int[][]{{18,45,20,25}});
    }

    public void menu1(){
        System.out.println("Morning");
        m.class_time_loop(period[0],0);
        System.out.println("Afternoon");
        f.class_time_loop(period[1],0);
        System.out.println("Evening");
        e.class_time_loop(period[2],0);
    }

    public void menu2(){
        choose_day();
        if(memory == 0){System.out.println("Welcome "+NameID.get(0));} else if (memory >=1 && memory <=7) {
            System.out.println("Morning");
            m.class_time_loop(period[0],memory);
            System.out.println("Afternoon");
            f.class_time_loop(period[1],memory);
            System.out.println("Evening");
            e.class_time_loop(period[2],memory);
        }else{
            System.out.println("wrong number");
            menu2();
        }
    }

    public void menu3(){
        choose_day();
        switch(memory){
            case 1:
                System.out.print("Monday course");
                check(0);
                break;
                case 2:
                    System.out.print("Tuesday course");
                    check(1);
                    break;
                    case 3:
                        System.out.print("Wednesday course");
                        check(2);
                        break;
                        case 4:
                            System.out.print("Thursday course");
                            check(3);
                            break;
                            case 5:
                                System.out.print("Friday course");
                                check(4);
                                break;
                                case 6:
                                    System.out.print("Saturday course");
                                    check(5);
                                    break;
                                    case 7:
                                        System.out.print("Sunday course");
                                        check(6);
                                        break;
        }
    }

    private void choose_day(){
        memory = 0;
        System.out.println("Which day? 1/2/3/4/5/6/7 [input 0 to back to the menu]");
        while(memory == 0){
            main.sc_clean();
            try {
                memory = main.Checkint(main.sc.nextInt(),0,7);
            } catch (InputMismatchException _) {
            }
        }
    }

    protected void class_time_loop(int amount,int type){
        if (type == 0) {
            for (int i = 1; i <= amount; i++) {
                System.out.println("Class " + i);
                System.out.print("enter start time (hours) [input]:()-():() :");
                time_sent(24, i - 1, 0);
                System.out.print("enter start time (minutes) ():[input]-():() :");
                time_sent(60, i - 1, 1);
                System.out.print("enter end time (hours) ():()-[input]:() :");
                time_sent(24, i - 1, 2);
                System.out.print("enter end time (minutes) ():()-():[input] :");
                time_sent(60, i - 1, 3);
                System.out.println("Class " + i + " write successfully");
            }
        }else if(type >=1 && type <=7){
                for (int i = 1; i <= amount; i++) {
                    System.out.println("Class " + i);
                    System.out.println("enter your course[input 0 to indicate no course at this time]");
                    course_sent(i-1,type-1);
                }
                System.out.println("Write successfully");
        }else{
            throw new NullPointerException();
        }
    }

    protected void time_sent(int max,int location1,int location2){
        main.sc_clean();
        try {
            memory = main.Checkint(main.sc.nextInt(),0,max);
        } catch (InputMismatchException e) {
            System.out.println("fail to input");
            memory = 0;
        }
            time[location1][location2] = memory;
    }

    protected void course_sent(int location1,int location2){
        main.sc_clean();
        String memory_string;
        try {
            memory_string = main.CheckString(main.sc.next(),2);
        } catch (InputMismatchException e) {
            System.out.println("fail to input");
            memory_string = null;
        }
        if (memory_string != null && memory_string.equals("0")) {
            memory_string = null;
        }
        course[location1][location2] = memory_string;
    }

    protected void check(int day){
        print_checktime(period[0],day,m.time,m.course);
        print_checktime(period[1],day,f.time,f.course);
        print_checktime(period[2],day,e.time,e.course);
    }

    private void print_checktime(int amount,int day,int[][] array1,String[][] array2){
        for(int i = 1;i<=amount;i++){
            System.out.println("Time: "+array1[i-1][0]+":"+String.format("%02d",array1[i-1][1])+"-"+array1[i-1][2]+":"+String.format("%02d",array1[i-1][3]));
            if(array2[i-1][day] == null){
                System.out.println("No course");
            }else {
                System.out.println("Course: "+array2[i-1][day]);
            }
        }
    }


}

class morning extends Time{
    protected morning() {
        this.time = new int[period[0]][4];
        this.course = new String[period[0]][7];
    }

    protected morning(int[][] a){
        this.time = a.clone();
        this.course = new String[period[0]][7];
    }
}

class afternoon extends Time{
    protected afternoon() {
        this.time = new int[period[1]][4];
        this.course = new String[period[1]][7];
    }

    protected afternoon(int[][] a){
        this.time = a.clone();
        this.course = new String[period[0]][7];
    }
}

class evening extends Time{
    protected evening() {
        this.time = new int[period[2]][4];
        this.course = new String[period[2]][7];
    }

    protected evening(int[][] a){
        this.time = a.clone();
        this.course = new String[period[0]][7];
    }

}
