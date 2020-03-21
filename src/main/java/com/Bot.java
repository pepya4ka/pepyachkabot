package com;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
//            sendMessage(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "Привет":
                    sendMsg(message, "Привет!");
                    break;
                case "привет":
                    String msg;
                    if (message.getFrom().getUserName().equals("sausageRoll") || message.getFrom().getUserName().equals("pepyachka"))
                        msg = "Привет, сосунок";
                    else
                        msg = "Привет, " + message.getFrom().getFirstName();
                    sendMsg(message, msg);
                    break;
                case "/help":
                    sendMsg(message, "Чем я могу помочь?");
                    break;
                case "/settings":
                    sendMsg(message, "Что будем настраивать?");
                    break;
                default:
            }
        }
    }

    public String getBotUsername() {
        return "che4yapka_bot";
    }

    public String getBotToken() {
        return "813000784:AAFVJu6h98HUWG0uo8t-OvWRnc3zG4HuIoY";
    }
}