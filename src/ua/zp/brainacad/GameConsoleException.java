package ua.zp.brainacad;

public class GameConsoleException extends Exception {
    public GameConsoleException() {
        super("Приставка завершает работу из-за отсутствия активности");
    }
}
