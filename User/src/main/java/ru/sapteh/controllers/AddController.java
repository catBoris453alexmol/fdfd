package ru.sapteh.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.Dao;
import ru.sapteh.daoImpl.UserDaoImpl;
import ru.sapteh.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddController {
    private final SessionFactory factory;
    public AddController(){
        factory = new Configuration().configure().buildSessionFactory();
    }
    List<User> listUsers = new ArrayList<>();
    @FXML
    private Button buttonAdd;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPass;

    @FXML
    private Label lblStatus;
    @FXML
    private Button buttonExit;

    public void initialize(){
        initUser();
    }

    public void initUser(){
        Dao<User, Integer> daoClient = new UserDaoImpl(factory);
        listUsers.addAll(daoClient.readByAll());
    }

    @FXML
    void AddUser(ActionEvent event) throws IOException {
        String login = "";
        Dao<User, Integer> daoUser = new UserDaoImpl(factory);
        User user = new User();
        for (User user1: listUsers) {
            if (user1.getLogin().equals(txtLogin.getText())){
                login = user1.getLogin();
            }
        }
        if (!txtLogin.getText().equals(login)) {
            user.setLogin(txtLogin.getText());
            user.setPassword(txtPass.getText());

            daoUser.create(user);
            lblStatus.setText("Пользователь" +  " " + txtLogin.getText() + " " + "создан");
        } else lblStatus.setText("Пользователь с таким логином уже существует");
    }
    @FXML
    void buttonExit(ActionEvent event) throws IOException {
        buttonExit.getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Reg.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Auto service");
        stage.setScene(new Scene(parent));
        stage.getIcons().addAll(new Image("/image/Alex.png"));
        stage.show();
    }
}



