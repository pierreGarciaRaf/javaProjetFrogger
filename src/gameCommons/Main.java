package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Timer;

import environment.EnvInf;
import environment.Environment;
import frog.Frog;
import graphicalElements.FroggerGraphicMulti;
import graphicalElements.IFroggerGraphics;
import frog.FrogInf;

public class Main {
	public static ArrayList<ArrayList<Integer>> controls;

	public static void setupControls() {
		controls = new ArrayList<ArrayList<Integer>>(0);
		ArrayList<Integer> controlFrog1 = new ArrayList<Integer>(0);
		controlFrog1.add(KeyEvent.VK_LEFT);
		controlFrog1.add(KeyEvent.VK_UP);
		controlFrog1.add(KeyEvent.VK_RIGHT);
		controlFrog1.add(KeyEvent.VK_DOWN);
		controls.add(controlFrog1);

		ArrayList<Integer> controlFrog2 = new ArrayList<Integer>(0);

		controlFrog2.add(KeyEvent.VK_Q);
		controlFrog2.add(KeyEvent.VK_Z);
		controlFrog2.add(KeyEvent.VK_D);
		controlFrog2.add(KeyEvent.VK_S);
		controls.add(controlFrog2);

		ArrayList<Integer> controlFrog3 = new ArrayList<Integer>(0);
		controlFrog3.add(KeyEvent.VK_K);
		controlFrog3.add(KeyEvent.VK_O);
		controlFrog3.add(KeyEvent.VK_M);
		controlFrog3.add(KeyEvent.VK_L);
		controls.add(controlFrog3);
	}

	public static void main(String[] args) {
		setupControls();
		int gameID = 0;
		int numberOfPlayers = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("chose your game-mode (1 for finite, 2 for endless) : ");
		while (!(gameID >= 1 && gameID <= 2)) {
			gameID = scan.nextInt();
		}
		System.out.println("chose the number of players (from 1 to 3) : ");
		while (!(numberOfPlayers >= 1 && numberOfPlayers <= 3)) {
			numberOfPlayers = scan.nextInt();
		}
		scan.close();

		// Caract�ristiques du jeu
		int width = 26;
		int height = 20 * gameID;
		int tempo = 100;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.05;

		IFroggerGraphics graphic = new FroggerGraphicMulti(width, height);
		Game game = new GameMulti(graphic, width, height, minSpeedInTimerLoops, defaultDensity);

		IEnvironment env = null;
		Frogs frogs = new Frogs();
		for (int playerID = 0; playerID < numberOfPlayers; playerID += 1) {
			switch (gameID) {
			case 2:
				frogs.add(new FrogInf(game, new Case(width - (playerID + 1) * width / (numberOfPlayers + 1), 1),
						Direction.up, controls.get(playerID)));
				break;
			case 1:
				frogs.add(new Frog(game, new Case(width - (playerID + 1) * width / (numberOfPlayers + 1), 0),
						Direction.up, controls.get(playerID)));
				break;
			}
			graphic.setFrog(frogs.get(playerID));
			game.addFrog(frogs.get(playerID));

		}
		switch (gameID) {
		case 1:
			env = new Environment(game);
			break;
		case 2:
			env = new EnvInf(game);
			break;
		}

		game.setEnvironment(env);

		// Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.update();
				graphic.repaint();
				game.time += tempo;
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}
