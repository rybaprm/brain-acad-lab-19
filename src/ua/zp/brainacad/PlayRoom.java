package ua.zp.brainacad;
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

import java.util.Arrays;
import java.util.Comparator;

public class PlayRoom {

    public static void main(String[] args) {
        Game.GameDisk[] disks = {
                Game.getDisk("game 1", Game.Ganre.ACTION, "first"),
                Game.getDisk("game 2", Game.Ganre.RACE, "second"),
                Game.getDisk("game 3", Game.Ganre.SPORT, "third"),
                Game.getDisk("game 4", Game.Ganre.ACTION, "fourth")
        };
        Game.VirtualGame[] virtualGames = {
                Game.getVirtualGame("virtual game 1", Game.Ganre.ACTION),
                Game.getVirtualGame("virtual game 2", Game.Ganre.RACE),
                Game.getVirtualGame("virtual game 3", Game.Ganre.SPORT),
                Game.getVirtualGame("virtual game 4", Game.Ganre.ACTION)
        };

        GameConsole gameConsole = new GameConsole(GameConsole.Brand.SONY, "S00001C");

        Arrays.sort(disks, new Comparator<Game.GameDisk>() {
            @Override
            public int compare(Game.GameDisk o1, Game.GameDisk o2) {
                return o1.getData().getGanre().compareTo(o2.getData().getGanre());
            }
        });

        Arrays.sort(virtualGames, new Comparator<Game.VirtualGame>() {
            @Override
            public int compare(Game.VirtualGame o1, Game.VirtualGame o2) {
                return Integer.compare(o1.getRating(), o2.getRating());
            }
        });

        /**
        Verification block of work anonymous instance Comparator
         */
//        for (Game.GameDisk disk : disks) {
//            System.out.println(disk.getData().getGanre());
//        }
//
//        for (Game.VirtualGame virtualGame : virtualGames) {
//            System.out.println(virtualGame.getRating());
//        }

        /**
         Verification block of work modifications of Class GameConsole
         */
        gameConsole.powerOn();
        gameConsole.loadGame(disks[1].getData());
//        gameConsole.getFirstGamepad().powerOn();
        gameConsole.getSecondGamepad().powerOn();
//        gameConsole.playGame();
//        gameConsole.getFirstGamepad().powerOff();
        for (int i = 0; i < 20; i++) {
            gameConsole.playGame();

        }
    }
}