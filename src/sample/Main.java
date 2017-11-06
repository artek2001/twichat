package sample;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.ArtekBot.Bot;

public class Main extends Application {
	public static Bot bot;
	public static Bot botSender;
    @Override
    public void start(Stage primaryStage) throws Exception {

        System.out.println("Launched");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        Controller.setController(controller);
        
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Twichat");
        primaryStage.setScene(new Scene(root, 450, 550));
        primaryStage.show();

    }

    public static void main(String[] args) {
        bot = new Bot();
        
        bot.connect();
        bot.joinChannel("#artek_tv");
        
        botSender = new Bot();
    	botSender.connect();
        botSender.joinChannel("#artek_tv");
        
        
        
        
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                bot.start();
                return null;
            }
        };
        new Thread(task).start();
        
        

        launch(args);

    }
}
