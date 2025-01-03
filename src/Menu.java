import java.util.InputMismatchException;

public class Menu {
    Main main;
    boolean setup = false;

    public Menu(String name) {
            System.out.println("welcome " + name);
            main = new Main(name);
            menu_setup(setup);
    }

    private void menu_setup(boolean setup) {
        if (setup) {
            main.line();
            System.out.println("Menu");
            System.out.println("(1)|set/change your timetable");
            System.out.println("(2)|set/change your course");
            System.out.println("(3)|check your course schedule");
            System.out.println("(4)|change user");
            System.out.println("(5)|exit");
            main.line();
            menu_choose(5);
        } else {
            main.line();
            System.out.println("Menu");
            System.out.println("(1)|setup");
            System.out.println("(2)|exit");
            main.line();
            menu_choose(2);
        }
    }

    private void menu_choose(int type) {
        main.sc_clean();
        int memory = 0;
        try {
            memory = main.Checkint(main.sc.nextInt(),1,type);
        } catch (InputMismatchException _) {
        }
        switch (type) {
            case 0:break;
            case 2:
                if (memory == 1) {
                    menu_setup_1();
                }else if (memory == 2) {
                    System.out.println("Exiting... bye bye");
                    System.out.println("Class schedule v2.0");
                    System.out.println("Team leader: Zirui Xu");
                    System.out.println("Team member: Chenshuo Wu , Xinyu Liu , Linbo Gu");
                    System.exit(0);
                }else{
                    System.out.println("Wrong choice,please try again");
                    menu_choose(2);
                }
                break;
                case 5:
                    switch (memory) {
                        case 1:
                            main.time.menu1();
                            menu_setup(setup);
                            break;
                            case 2:
                                main.time.menu2();
                                menu_setup(setup);
                                break;
                                case 3:
                                    main.time.menu3();
                                    menu_setup(setup);
                                    break;
                        case 4:
                            main = new Main();
                            break;
                                    case 5:
                                        System.out.println("Exiting... bye bye");
                                        System.out.println("Class schedule v2.0");
                                        System.out.println("Team leader: Zirui Xu");
                                        System.out.println("Team member: Chenshuo Wu , Xinyu Liu , Linbo Gu");
                                        System.exit(0);
                    }
        }
    }

    public void menu_setup_1(){
        System.out.println("now here it's time for you to do some setup.");
        System.out.println("You can try to use the default class amounts and class timetable.  Y/N");
        main.Y_N( 1);
        setup = true;
        menu_setup(setup);
    }


}
