package ua.zp.brainacad;
//1. Реализация внутреннего класса
//        • Создать класс GameConsole.
//        Описать поля:
//        brand (название производителя, например Sony, Microsoft. Можно оформить
//enum-ом),
//        model (название модели, например XBOX 360, PS4 PRO),
//        serial (серийный номер приставки, например XC123QeWR),
//        firstGamepad (объект для первого джойстика, который будет реализован как
//        внутренний класс),
//        secondGamepad (объект для второго джойстика),
//        isOn (флаг состояния. True – вкл, false - выкл)
//        • Создать внутренний (нестатический) класс Gamepad.
//        Описать поля:
//        brand (название производителя, например Sony, Microsoft).
//        consoleSerial (серийный номер консоли, к которой подключен джойстик),
//        connectedNumber (порядковый номер джойстика),
//        color (цвет джойстика, можно оформить enum-ом),
//        chargeLevel (значение процента заряда, по умолчанию поставить 100.0)
//        isOn (флаг состояния. True – вкл, false - выкл).
//        • Создать конструктор для класса GameConsole. Передать в него 2 параметра
//        (brand, serial)
//        • Создать конструктор для класса Gamepad. Передать в него параметр (brand и
//        connectedNumber), а полю consoleSerial присвоить значение серийного
//        номера приставки.
//        • Внутри конструктора создать и присвоить 2 джойстика (firstGamepad,
//        secondGamepad). Причем brand можно передать уже существующие для самой
//        консоли, а connectedNumber фиксированными значениями 1 и 2.
//        • Для полей которые не должны меняться (определите их сами :) ), применить
//        модификатор final, и создать геттеры.
//        • Для остальных полей создать геттеры и сеттеры.

//        5* Доработать класс GameConsole
//        • Создать интерфейс Powered с методами powerOn и PowerOff
//        • Реализовать данный интерфейс для джойстика и консоли
//        • Добавить функционал, который включает приставку, когда включается
//        джойстик.
//        • Добавить функционал, который делает «второй» джойстик «первым», если
//        первый был выключен.
//        • Добавить поле Game activeGame
//        • Добавить метод loadGame(Game). В нем вывести сообщение «Игра
//        {название} загружается»
//        • Добавить метод playGame(). В нем выводить информацию о текущей игре
//        «Играем в {игра}» и информацию о заряде только активных джойстиков.
//        Внимание! При каждом вызове метода – уменьшать заряд джойстика на
//        10%. Когда заряд уменьшиться до 0 – нужно выключить джойстик.
//        • Добавить приватный метод void checkStatus(). Который будет вызываться
//        каждый раз когда вызывается метод playGame().
//        Добавить новое поле для класса GameConsole – waitingCounter;
//        Если оба джойстика выключены – выводить сообщение «Подключите
//        джойстик» и увеличивать счетчик на 1. Если хотя-бы один джойстик
//        активен – сбрасывать в 0.
//        Если счетчик превысил 5 циклов ожидания – «Выключить» приставку и
//        бросить исключение с текстом «Приставка завершает работу из-за
//        отсутствия активности» (Класс-исключение создать свой.)
public class GameConsole implements Powered {
    private final Brand brand;
    private String model;
    private final String serial;
    private final Gamepad firstGamepad;
    private final Gamepad secondGamepad;
    private boolean isOn;
    private Game activeGame;
    public int waitingCounter;

    public class Gamepad implements Powered {
        private final Brand brand;
        private final String consoleSerial;
        private int connectedNumber;
        private Color color;
        private double chargeLevel = 100.0;
        private boolean isOn;

        public Gamepad(Brand brand, int connectedNumber) {
            this.brand = brand;
            this.connectedNumber = connectedNumber;
            this.consoleSerial = serial;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public double getChargeLevel() {
            return chargeLevel;
        }

        public void setChargeLevel(double chargeLevel) {
            this.chargeLevel = chargeLevel;
        }

        public boolean isOn() {
            return isOn;
        }

        public void setOn(boolean on) {
            isOn = on;
        }

        @Override
        public void powerOn() {
            isOn = true;
            GameConsole.this.isOn = true;
            if (firstGamepad.isOn) {
                secondGamepad.connectedNumber = 2;
            } else {
                secondGamepad.connectedNumber = 1;
                System.out.println("Второй джойстик включился как первый,так как первый выключен.");
            }
        }

        @Override
        public void powerOff() {
            isOn = false;
            if (!firstGamepad.isOn && secondGamepad.isOn) {
                secondGamepad.connectedNumber = 1;
                System.out.println("Второй джойстик стал первым,так как первый был выключен.");
            }
        }
    }

    public GameConsole(Brand brand, String serial) {
        this.brand = brand;
        this.serial = serial;
        firstGamepad = new Gamepad(brand, 1);
        secondGamepad = new Gamepad(brand, 2);

    }

    public Gamepad getFirstGamepad() {
        return firstGamepad;
    }

    public Gamepad getSecondGamepad() {
        return secondGamepad;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void powerOn() {
        isOn = true;
    }

    @Override
    public void powerOff() {
        isOn = false;
    }

    public void loadGame(Game game) {
        System.out.printf("%nИгра {%s} загружается%n", game.getName());
        activeGame = game;
    }

    public void playGame() {
        if (isOn) {
            System.out.printf("%nИграем в {%s}%n", activeGame.getName());
            Gamepad[] gamepads = {firstGamepad, secondGamepad};
            for (Gamepad gamepad : gamepads) {
                if (gamepad.chargeLevel == 0.0) {
                    gamepad.powerOff();
                }
                if (gamepad.isOn) {
                    System.out.printf("%nЗаряд джойстика №%d - %.0f%n", gamepad.connectedNumber, gamepad.chargeLevel);
                    gamepad.chargeLevel -= 10.0;
                }
            }
            checkStatus();
        } else {
            System.out.println("Приставка выключена. Играть не получится.");
            ;
        }
    }

    private void checkStatus() {
        if (!firstGamepad.isOn & !secondGamepad.isOn & isOn) {
            System.out.println("Подключите джойстик");
            waitingCounter++;
        } else {
            waitingCounter = 0;
        }

        try {
            if (waitingCounter > 5) {
                throw new GameConsoleException();
            }
        } catch (GameConsoleException e) {
            System.out.println(e.getMessage());
            powerOff();
        }
    }


    public enum Brand {
        SONY,
        MICROSOFT;

    }

    public enum Color {
        RED,
        BLACK,
        WHITE;
    }
}
