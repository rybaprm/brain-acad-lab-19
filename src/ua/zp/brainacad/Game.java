package ua.zp.brainacad;
//        2. Реализация вложенного статического класса
//        • Создать класс Game.
//        Описать поля (пометить final):
//        name (название игры),
//        ganre (жанр игры, например ACTION, SPORT, RACE. Можно оформить enumом),
//        type (тип носителя, например VIRTUAL, PHYSICAL. Можно оформить enumом прямо внутри класса Game),
//        • Создать приватный конструктор. В конструктор передать поля: name, ganre,
//        type.
//        • Создать getter-ы для полей.
//        • Создать вложенный (статический) класс GameDisk.
//        • Создать поле description (описание игры, пометить final)
//        • Создать поле Game data (final);
//        • В приватный конструктор передать поля: name, ganre, description.
//        • При вызове конструктора создавать экземпляр класса Game и
//        передавать в него параметры name, ganre и фиксированный type
//        соответствующий этому носителю.
//        • Также инициализировать поле с описанием.
//        • Создать геттеры
//        • Создать вложенный (статический) класс VirtualGame.
//        • Создать поле rating (рейтинг игры от 0 до 5)
//        • Создать поле Game data (final);
//        • В приватный конструктор передать поля: name, ganre.
//        • При вызове конструктора создавать экземпляр класса Game и
//        передавать в него параметры name, ganre и фиксированный type
//        соответствующий этому носителю.
//        • Создать необходимые геттеры/сеттеры
//        • В классе Game создать статический метод GameDisk getDisk(…){…} для
//        получения экземпляра класса (Паттерн static factory)
//        • В метод передать параметры name, ganre, description
//        • Внутри метода создать и вернуть экземпляр класса GameDisk
//        • Аналогично, в классе Game создать статический метод VirtualGame
//        getVirtualGame (…){…}
//        • В метод передать параметры name, ganre
//        • Внутри метода создать и вернуть экземпляр класса VirtualGame.
//        3. Создать класс PlayRoom
//        • Создать main метод.
//        • Создать массив с играми на физ. носителях (4 игры). (пользуемся методом
//        getDisk)
//        • Создать массив с играми из виртуального магазина (4 игры). (пользуемся
//        методом getVirtualGame)
//        • Создать GameConsole.
//        4. Реализация анонимного класса (Comparator)
//        • В методе main, отсортировать массив с физическими дисками по жанру.
//        • В методе main, отсортировать массив с виртуальными играми по рейтингу.
//        Для сравнения примитивов можно воспользоваться методом :
//        Integer.compare(a.getRating(), b.getRating())
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
public class Game {
    private final String name;
    private Ganre ganre;
    private DriveType type;

    private Game(String name, Ganre ganre, DriveType type) {
        this.name = name;
        this.ganre = ganre;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Ganre getGanre() {
        return ganre;
    }

    public DriveType getType() {
        return type;
    }

    public static class GameDisk {
        public final String description;
        private final Game data;

        private GameDisk(String name, Ganre ganre, String description) {
            data = new Game(name, ganre, DriveType.PHYSICAL);
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public Game getData() {
            return data;
        }
    }

    public static class VirtualGame {
        private int rating = (int) (Math.random() * 5);
        private final Game data;

        private VirtualGame(String name, Ganre ganre) {
            data = new Game(name, ganre, DriveType.VIRTUAL);
        }

        public int getRating() {
            return rating;
        }

        public Game getData() {
            return data;
        }


    }

    public static GameDisk getDisk(String name, Ganre ganre, String description) {
        return new GameDisk(name, ganre, description);
    }

    public static VirtualGame getVirtualGame(String name, Ganre ganre) {
        return new VirtualGame(name, ganre);
    }


    public enum DriveType {
        VIRTUAL,
        PHYSICAL;
    }

    public enum Ganre {
        ACTION,
        SPORT,
        RACE;
    }

}
