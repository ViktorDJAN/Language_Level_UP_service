package ru.kashtanov.graduation_work.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kashtanov.graduation_work.models.Result;
import ru.kashtanov.graduation_work.services.ResultService;
import ru.kashtanov.graduation_work.models.Student;
import ru.kashtanov.graduation_work.services.StudentService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component

public class TelegramBot extends TelegramLongPollingBot {
    final BotConfig botConfig;
    private final ResultService resultService;
    private final StudentService studentService;

    private final Map<String, String> bindingBy = new ConcurrentHashMap<>();

    public TelegramBot(BotConfig botConfig, ResultService resultService, StudentService studentService) {
        this.botConfig = botConfig;
        this.resultService = resultService;
        this.studentService = studentService;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    boolean startWait = false;

    @Override                                      // и обрабатыватся
    public void onUpdateReceived(Update update) {  // то и есть точка входа, куда будут поступать сообщения от пользователей.
        long chatId = update.getMessage().getChatId();
        if (update.hasMessage() && update.getMessage().hasText() && startWait == false) {
            String command = update.getMessage().getText();

            if (command.equals("/start")) {
                sendMessage(chatId, "Hello and welcome to language level up bot!");
                sendMessage(chatId, "If you are wondering to get results of the English test of  students");
                sendMessage(chatId, "Firstly enter that command: /get_result ");

            }
            if (command.equals("/get_result")) {
                sendMessage(chatId, "Further enter a student's firstname");
                startWait = true;
            }
        } else if (update.hasMessage() && update.getMessage().hasText() && startWait == true) {
            String firstName = update.getMessage().getText();
            Student student = studentService.getCertainStudentByFirstName(firstName);
            Result result = resultService.getLastStudentResultById(student.getId());

            if (result == null) {
                sendMessage(chatId, "There is no such student with full name " + student.getFirstName() + " "
                        + student.getSecondName());
            } else {
                sendMessage(chatId, "Student " + student.getFirstName() + " " + student.getSecondName());
                sendMessage(chatId, "Has next results:  english level =  " + result.getResultName() +
                        ", quantity of right answers is equaled " + result.getScore());
            }
            startWait = false;
        }
    }


    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("error occurred in sendMessage method" + e);
        }


    }
}
