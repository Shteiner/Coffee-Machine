package machine;

import java.util.Scanner;

public class CoffeeMachine {
    enum State {
        WAIT,
        BUY,
        WATER,
        MILK,
        COFFEE,
        CUPS,
        EXIT
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine();
        do {
            if (machine.getState() == State.WAIT) {
                machine.wellcome();
            }
            machine.action(scanner.nextLine());
        } while (machine.getState() != State.EXIT);
    }

    int water;
    int milk;
    int coffee;
    int cups;
    int money;
    private State state;

    public void setState(State state) {
        this.state = state;
        if (this.state == State.WATER) {
            System.out.println("Write how many ml of water do you want to add:");
        }
        if (this.state == State.MILK) {
            System.out.println("Write how many ml of milk do you want to add:");
        }
        if (this.state == State.COFFEE) {
            System.out.println("Write how many grams of coffee beans do you want to add:");
        }
        if (this.state == State.CUPS) {
            System.out.println("Write how many disposable cups of coffee do you want to add:");
        }
        if (this.state == State.BUY) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        }
    }
    public State getState() {
        return this.state;
    }
    public void action(String s) {
        int value = 0;
        switch (this.state) {
            case WAIT:
                switch (s) {
                    case "buy":
                        this.setState(State.BUY);
                        break;
                    case "fill":
                        this.setState(State.WATER);
                        break;
                    case "take":
                        this.takeMoney();
                        break;
                    case "remaining":
                        this.printStatus();
                        break;
                    case "exit":
                        this.setState(State.EXIT);
                        break;
                    default:
                        System.out.println("Something wrong");
                        break;
                }
                break;
            case WATER:
                value = Integer.parseInt(s);
                this.fillMachine(value, 0, 0, 0);
                this.setState(State.MILK);
                break;
            case MILK:
                value = Integer.parseInt(s);
                this.fillMachine(0, value, 0, 0);
                this.setState(State.COFFEE);
                break;       
            case COFFEE:
                value = Integer.parseInt(s);
                this.fillMachine(0, 0, value, 0);
                this.setState(State.CUPS);
                break; 
            case CUPS:
                value = Integer.parseInt(s);
                this.fillMachine(0, 0, 0, value);
                this.setState(State.WAIT);
                break;
            case BUY:
                switch (s) {
                    case "1":
                        this.buyEspresso();
                        break;
                    case "2":
                        this.buyLatte();
                        break;
                    case "3":
                        this.buyCappuccino();
                        break;
                    default:
                        System.out.println("Something wrong");
                        break;
                }
                this.setState(State.WAIT);
                break;
            default:
                break;
        }
    }
    public void wellcome() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }
    public void takeMoney() {
        System.out.println("I gave you " + this.money);
        this.money = 0;
    }
    private boolean buy(int water, int milk, int coffee, int money) {
        if (this.water < water) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (this.milk < milk) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (this.coffee < coffee) {
            System.out.println("Sorry, not enough coffee!");
            return false;
        } else if (this.cups == 0) {
            System.out.println("Sorry, not enough cup!");
            return false;
        } else {
            this.water -= water;
            this.milk -= milk;
            this.coffee -= coffee;
            this.cups -= 1;
            this.money += money;
            System.out.println("I have enough resources, making you a coffee!");
            return true;
        }
    }
    public void buyEspresso() {
        this.buy(250, 0, 16, 4);
    }
    public void buyLatte() {
        this.buy(350, 75, 20, 7);
    }
    public void buyCappuccino() {
        this.buy(200, 100, 12, 6);
    }
    public void fillMachine(int water, int milk, int coffee, int cups) {
        this.water += water;
        this.milk += milk;
        this.coffee += coffee;
        this.cups += cups;
    }
    CoffeeMachine(int water, int milk, int coffee, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cups = cups;
        this.money = money;
        this.state = State.WAIT;
    }
    public CoffeeMachine() {
        this(400, 540, 120, 9, 550);
    }
    public void printStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(this.water + " of water");
        System.out.println(this.milk + " of milk");
        System.out.println(this.coffee + " of coffee beans");
        System.out.println(this.cups + " of disposable cups");
        System.out.println(this.money + " of money");
    }
}
