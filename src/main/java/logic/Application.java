package logic;

import data.Player;

import java.util.Scanner;

/**
 * @author Andrew Nuzha
 * @version 1.1
 */
public class Application {

    private static boolean shouldApplicationFinish = false;

    public static void setApplicationExitCondition() {
        shouldApplicationFinish = true;
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);
        try {
            Player humanPlayer = new Player();
            Player botPlayer = new Player();
            if (new InputOperator().isManualPlacementRequired(scanner)) {
                humanPlayer.manualShipPlacer().placeShips(humanPlayer.shipsOperations().getShips(), scanner);
            } else {
                humanPlayer.automaticShipPlacer().placeShips(humanPlayer.shipsOperations().getShips());
            }
            botPlayer.automaticShipPlacer().placeShips(botPlayer.shipsOperations().getShips());

            if (!shouldApplicationFinish) {
                Battle seaBattle = new Battle(humanPlayer, botPlayer, scanner);
                seaBattle.startBattle();
            }
        } catch (InterruptedException intEx) {
            System.err.println("There is a problem with thread");
        } catch (Exception ex) {
            System.err.println("Something went wrong " + ex.getMessage());
        } finally {
            scanner.close();
        }
    }
}
