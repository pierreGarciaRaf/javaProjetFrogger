package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Timer;

import environment.EnvInf;
import environment.Environment;
import frog.Frog;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;
import frog.FrogInf;

public class Main {
	public static void main(String[] args) {
		int gameID = 0;
		Scanner scan = new Scanner(System.in);
		while (!(gameID == 1 || gameID == 2)) {
			gameID = scan.nextInt();
		}
		scan.close();

		// Caract�ristiques du jeu
		int width = 26;
		int height = 20*gameID;
		int tempo = 100;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.2;

		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);

		IFrog frog = null;
		IEnvironment env = null;

		switch (gameID) {
		case 2:
			frog = new FrogInf(game);
			env = new EnvInf(game, frog);
			break;
		case 1:
			frog = new Frog(game);
			env = new Environment(game);
			break;
		}

		game.setFrog(frog);
		graphic.setFrog(frog);
		game.setEnvironment(env);

		// Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.update();
				graphic.repaint();
				game.time+=tempo;
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}
