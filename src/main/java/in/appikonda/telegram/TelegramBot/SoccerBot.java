package in.appikonda.telegram.TelegramBot;

import static java.lang.Math.toIntExact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class SoccerBot extends TelegramLongPollingBot  {
	 @Override
	    public void onUpdateReceived(Update update) {

	        // We check if the update has a message and the message has text
	        if (update.hasMessage() && update.getMessage().hasText()) {
	            String message_text = update.getMessage().getText();
	            long chat_id = update.getMessage().getChatId();
	            if (update.getMessage().getText().equals("/start")) {


	                SendMessage message = new SendMessage() // Create a message object object
	                        .setChatId(chat_id)
	                        .setText("You send /start");
	                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
	                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
	                List<InlineKeyboardButton> rowInline = new ArrayList<>();
	                rowInline.add(new InlineKeyboardButton().setText("Update message text").setCallbackData("update_msg_text"));
	                // Set the keyboard to the markup
	                rowsInline.add(rowInline);
	                // Add it to the message
	                markupInline.setKeyboard(rowsInline);
	                message.setReplyMarkup(markupInline);
	                try {
	                    sendMessage(message); // Sending our message object to user
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	            } else if (update.getMessage().getText().equals("/fixtures")) {
	            	SendMessage message = new SendMessage()
	                        .setChatId(chat_id)
	                        .setText("Fixtures");
	                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
	                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
	                List<InlineKeyboardButton> rowInline = new ArrayList<>();
	                
	                
	                String url = "http://api.football-data.org/v1/competitions/464/fixtures";

	                HttpClient client = HttpClientBuilder.create().build();
	                HttpGet request = new HttpGet(url);

	                // add request header
	                request.addHeader("accept", "application/xml");
	                
	                StringBuffer result = new StringBuffer();
	                HttpResponse response;
					try {
						response = client.execute(request);
					

	                System.out.println("Response Code : "
	                                + response.getStatusLine().getStatusCode());

	                BufferedReader rd = new BufferedReader(
	                	new InputStreamReader(response.getEntity().getContent()));

	            
	                String line = "";
	                while ((line = rd.readLine()) != null) {
	                	result.append(line);
	                }
	                
					} catch (ClientProtocolException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                
	                rowInline.add(new InlineKeyboardButton().setText("today").setCallbackData("update_msg_text"));
	                // Set the keyboard to the markup
	                rowsInline.add(rowInline);
	                // Add it to the message
	                markupInline.setKeyboard(rowsInline);
	                message.setReplyMarkup(markupInline);
	                try {
	                    sendMessage(message); // Sending our message object to user
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }

	            }

	        } else if (update.hasCallbackQuery()) {
	            // Set variables
	            String call_data = update.getCallbackQuery().getData();
	            long message_id = update.getCallbackQuery().getMessage().getMessageId();
	            long chat_id = update.getCallbackQuery().getMessage().getChatId();

	            if (call_data.equals("update_msg_text")) {
	                String answer = "Updated message text";
	                EditMessageText new_message = new EditMessageText()
	                        .setChatId(chat_id)
	                        .setMessageId(toIntExact(message_id))
	                        .setText(answer);
	                try {
	                    editMessageText(new_message);
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
 	    }
	    @Override
	    public String getBotUsername() {
	        // Return bot username
	        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
	        return "CLBetBot";
	    }

	    @Override
	    public String getBotToken() {
	        // Return bot token from BotFather
	        return "418043233:AAHJf2UV2ii8nWxTZ3wt8vq_ffj5lC31jX8";
	    }
}
