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

public class UserController {
    private final SessionFactory factory;
    public static String role;
    public UserController() {
        factory = new Configuration().configure().buildSessionFactory();
    }
    ObservableList<User> listUsers = FXCollections.observableArrayList();

    @FXML
    private Button buttonSignIn;

    @FXML
    private Button buttonSignUp;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtLogin;

    @FXML
    private Label lblInfo;
    @FXML
    public void initialize() {
        initUsers();
    }

    public void initUsers() {
        Dao<User, Integer> daoUser = new UserDaoImpl(factory);
        listUsers.addAll(daoUser.readByAll());
    }

    @FXML
    void SignIn(ActionEvent event) throws IOException {
        for (User user: listUsers) {

            if (user.getLogin().equals(txtLogin.getText()) && user.getPassword().equals(txtPass.getText())){
                buttonSignIn.getScene().getWindow().hide();
                lblInfo.setText("Вход...");
            } else lblInfo.setText("Вы ввели неверный логин или пароль. Пожалуйста проверьте ещё раз введенные данные");
        }
    }

    @FXML
    void SignUp(ActionEvent event) throws IOException {
        buttonSignUp.getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/View/AddUser.fxml"));
        Stage stage = new Stage();
        stage.setTitle("AddUser");
        stage.setScene(new Scene(parent));
        stage.getIcons().add(new Image("/image/unnamed.png"));
        stage.show();
    }

}

