package sample;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import sample.ArtekBot.Bot;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.sun.prism.paint.Paint;


public class Controller  implements Initializable {
    public static  Controller controller;
    public static String message;
    //@FXML public Label labelChat;
    @FXML public GridPane gridPane;  
    @FXML public VBox vbox;
    @FXML public ScrollPane scrollPane;
    @FXML public TextArea textArea;
    @FXML public Label viewCount;
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	Main.bot.currentChannel = Main.bot.getChannels().get(0);
		vbox.setSpacing(30);

        System.out.println("initialized");
        updateViewCount();
        
        new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				Platform.runLater(() -> { Controller.controller.viewCount
					.setText("Viewers: " + Main.bot.getChannels().get(0).getViewersNum()); });
				
			}
		}, 0, 10000);

        vbox.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				scrollPane.setVvalue(1.0);
			}
		});
        
    }

    public static void setController(Controller controller) {
        Controller.controller = controller;
        System.out.println("SET");
    }
    
    public void addLabel(String text) {

//    	String resultText = "";
//    	String tempText = "";
//		int iteration = 1;
//
//    	char[] countChars = text.toCharArray();
//    	double height = ((countChars.length % 39) + 1) * 20;
//
//
//    	System.out.println(countChars);
//    	for(int i = 0; i < countChars.length; i++) {
//
//    		if (i % 42 == 0 && i != 0) {
//				resultText = resultText + countChars[i] + '\n';
//				continue;
//			}
//
//    		resultText += countChars[i];
//    	}
//		System.out.println(resultText);
		System.out.println(text);
		Label label = new Label(text);
		label.setMaxWidth(vbox.getMaxWidth());
		label.setWrapText(true);


   
    	final double MAX_FONT_SIZE = 18.0; // define max font size you need
    	label.setFont(new Font(MAX_FONT_SIZE)); // set to Label
    	
    	label.setTextFill(Color.color(Math.random(), Math.random(), Math.random()));
    	label.setLayoutX(0);
    	
    	if(vbox.getChildren().size() >= 1) {
    		double yCordinate = vbox.getChildren().get(vbox.getChildren().size()-1).getBoundsInParent().getHeight();
//    		label.setLayoutY(vbox.getChildren().get(vbox.getChildren().size()-1).getLayoutY() + 50 + yCordinate);

    	}

    	this.vbox.getChildren().add(label);

//    	this.scrollPane.setMaxHeight(pane.getMaxHeight() + pane.getChildren().get(pane.getChildren().size()-1).getBoundsInParent().getHeight());
    	
//    	scrollPane.setVvalue(1.0);
//    	if (pane.getChildren().get(pane.getChildren().size()-1).getLayoutY() > 450) {
//			pane.setPrefHeight(pane.getChildren().get(pane.getChildren().size()-1).getLayoutY() + 50);
//		}
    	
    	//this.pane.getChildren().add(new Label)
    }
    
    @FXML
    public void onEnter(KeyEvent event) {
    	
    	String textMessage = textArea.getText();
    	
    	if(event.getCode() == KeyCode.ENTER) {
    		
    		Main.botSender.sendMessage(textMessage, Main.bot.currentChannel);
    		textArea.clear();
    		event.consume();
    	}
    	
    	
    }

    //TODO
    
	public void updateViewCount() {
			
	}
}