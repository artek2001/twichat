package sample.ArtekBot;

import javafx.application.Platform;
import javafx.scene.control.Label;
import sample.Chat.Channel;
import sample.Chat.User;
import sample.Controller;
import sample.Main;
import sample.Core.TwitchBot;

import java.util.Date;

public class Bot extends TwitchBot {
	public Channel currentChannel;
    public Bot() {
        this.setUsername("artekbot");
        this.setOauth_Key("oauth:9s5rcn2468z2xvsdgzoxlq0iz2czk0");
        this.setClientID("u63mrvhczu0twk08lalt0cwlrq4vni");
    }


    @Override
    public void onMessage(User user, Channel channel, String message) {
        Date date = new Date();

    	String message2 = "[" + date.toLocaleString().replaceAll("^(\\S+\\s+){1}", "") + "]" + "" + user+ ":  " + message;
    	String message3 = message2.replaceAll("\\A([^\\r\\n]*\\n){1}", "");
        System.out.println("Message is:" + message);
        
        //Platform.runLater( () -> { Controller.controller.labelChat.setText(message); });
        Platform.runLater( () -> {Controller.controller.addLabel(message3);} );
        
    }

}
