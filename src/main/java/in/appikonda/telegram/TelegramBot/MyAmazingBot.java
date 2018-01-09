package in.appikonda.telegram.TelegramBot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class MyAmazingBot extends TelegramLongPollingBot {
   
	

	    public void onUpdateReceived(Update update) {
	    	this.userInfo(update);
	    	
	
	        // We check if the update has a message and the message has text
	        if (update.hasMessage() && update.getMessage().hasText()) {
	            // Set variables
	            String message_text = update.getMessage().getText();
	            long chat_id = update.getMessage().getChatId();
	            if (message_text.equals("/start")) {
	                SendMessage message = new SendMessage() // Create a message object object
	                        .setChatId(chat_id)
	                        .setText(message_text);
	                try {
	                    sendMessage(message); // Sending our message object to user
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	            } else if (message_text.equals("/pic")) {
	            	
	                SendPhoto msg = new SendPhoto()
	                        .setChatId(chat_id)
	                        .setPhoto("AgADAgAD6qcxGwnPsUgOp7-MvnQ8GecvSw0ABGvTl7ObQNPNX7UEAAEC")
	                        .setCaption("Photo");
	                try {
	                    sendPhoto(msg); // Call method to send the photo
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	            } else if (message_text.equals("/name")) {
	            	SendMessage msg = new SendMessage()
	                        .setChatId(chat_id)
	                        .setText(update.getMessage().getChat().getFirstName() +" "+update.getMessage().getChat().getLastName()  );
            	 try {
	                    sendMessage(msg); // Call method to send the photo
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	            	
	            	
	                /*SendMessage message = new SendMessage() // Create a message object object
	                        .setChatId(chat_id)
	                        .setText("Here is your keyboard");
	                // Create ReplyKeyboardMarkup object
	                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
	                // Create the keyboard (list of keyboard rows)
	                List<KeyboardRow> keyboard = new ArrayList<>();
	                // Create a keyboard row
	                KeyboardRow row = new KeyboardRow();
	                // Set each button, you can also use KeyboardButton objects if you need something else than text
	                row.add("Row 1 Button 1");
	                row.add("Row 1 Button 2");
	                row.add("Row 1 Button 3");
	                // Add the first row to the keyboard
	                keyboard.add(row);
	                // Create another keyboard row
	                row = new KeyboardRow();
	                // Set each button for the second line
	                row.add("Row 2 Button 1");
	                row.add("Row 2 Button 2");
	                row.add("Row 2 Button 3");
	                // Add the second row to the keyboard
	                keyboard.add(row);
	                // Set the keyboard to the markup
	                keyboardMarkup.setKeyboard(keyboard);
	                // Add it to the message
	                message.setReplyMarkup(keyboardMarkup);
	                try {
	                    sendMessage(message); // Sending our message object to user
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }*/
	            }
	            else if (message_text.equals("Row 1 Button 1")) {
	                SendPhoto msg = new SendPhoto()
	                        .setChatId(chat_id)
	                        .setPhoto("AgADAgAD6qcxGwnPsUgOp7-MvnQ8GecvSw0ABGvTl7ObQNPNX7UEAAEC")
	                        .setCaption("Photo");

	                try {
	                    sendPhoto(msg); // Call method to send the photo
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	            } else if (message_text.equals("/hide")) {
	                SendMessage msg = new SendMessage()
	                        .setChatId(chat_id)
	                        .setText("Keyboard hidden");
	                ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
	                msg.setReplyMarkup(keyboardMarkup);
	                try {
	                    sendMessage(msg); // Call method to send the photo
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	            } else if(message_text.equals("/username")) {
	            	 SendMessage msg = new SendMessage()
		                        .setChatId(chat_id)
		                        .setText("@"+update.getMessage().getChat().getUserName());
	            	 try {
		                    sendMessage(msg); // Call method to send the photo
		                } catch (TelegramApiException e) {
		                    e.printStackTrace();
		                }
	            }
	            else {
	                SendMessage message = new SendMessage() // Create a message object object
	                        .setChatId(chat_id)
	                        .setText("Unknown command");
	                try {
	                    sendMessage(message); // Sending our message object to user
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	            }
	        } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
	            // Message contains photo
	            // Set variables
	            long chat_id = update.getMessage().getChatId();

	            List<PhotoSize> photos = update.getMessage().getPhoto();
	            String f_id = photos.stream()
	                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
	                    .findFirst()
	                    .orElse(null).getFileId();
	            int f_width = photos.stream()
	                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
	                    .findFirst()
	                    .orElse(null).getWidth();
	            int f_height = photos.stream()
	                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
	                    .findFirst()
	                    .orElse(null).getHeight();
	            String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
	            SendPhoto msg = new SendPhoto()
	                    .setChatId(chat_id)
	                    .setPhoto(f_id)
	                    .setCaption(caption);
	            try {
	                sendPhoto(msg); // Call method to send the message
	            } catch (TelegramApiException e) {
	                e.printStackTrace();
	            }
	        }
	    }



 
    public String getBotUsername() {
        // TODO
        return  "BoutYou_bot";
    }
// This method must always return your Bot Token (If you don't know it, you may want to talk with @BotFather)
    @Override
    public String getBotToken() {
        // TODO
        return "440040743:AAGoa986qHZa8bctV3AysIt_q50zVH0Y1XA";
    }
    
    
    private void userInfo(Update update) {
    	
    	String user_first_name = update.getMessage().getChat().getFirstName();
    	
        String user_last_name = update.getMessage().getChat().getLastName();
        String user_username = update.getMessage().getChat().getUserName();
        long user_id = update.getMessage().getChat().getId();
        System.out.println(user_username);
        
        

    }
}