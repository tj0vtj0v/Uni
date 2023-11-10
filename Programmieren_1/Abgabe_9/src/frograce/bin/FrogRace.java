package frograce.bin;

import frograce.game.*;
import frograce.printer.GameViewPrinter;

public class FrogRace {
    private final Game game;
    private final GameViewPrinter gameViewPrinter;


    public static void main(String[] args) {
        new FrogRace().startGameLoop();
    }

    FrogRace() {
        Frog[] frogs = new Frog[]{
                new Frog("Hugo", .1, .1, 1),
                new Frog("Willi", .1, .1, 1),
                new Frog("Frank", .1, .1, 1),
                new Frog("Rudolf", .1, .1, 1),
        };

        String trackName = "Monte Carlo";
        int trackLength = 600;
        game = new Game(trackName, trackLength, frogs);

        gameViewPrinter = new GameViewPrinter(game, trackLength);
        gameViewPrinter.printAndSleep(1000);
    }

    private void startGameLoop() {
        while (!game.isGameOver()) {
            game.nextIteration();
            gameViewPrinter.printAndSleep(300);
        }

    }

}

