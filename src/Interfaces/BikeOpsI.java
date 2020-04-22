/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Souhaiel
 */
public class BikeOpsI extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("MenuFront.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Menu");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static AudioStream as;
    static FileInputStream blah;

    public static void songs(String word) throws IOException {
        String temp = word;
        if (temp.equals("go")) {
            try {
                blah = new FileInputStream("C:/a.wav");
            } catch (Throwable e) {
                e.printStackTrace();
            }

            as = new AudioStream(blah);
            AudioPlayer.player.start(as);
            System.out.println("playing");
        }
        if (temp.equals("stop")) {
            if (as != null) {
                AudioPlayer.player.stop(as);
            }
            System.out.println("stopping2");
            AudioPlayer.player.stop(as);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
