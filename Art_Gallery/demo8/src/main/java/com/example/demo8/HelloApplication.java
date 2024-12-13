package com.example.demo8;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import java.io.*;

public class HelloApplication extends Application {
    private VBox loginForm;
    private VBox newPasswordForm;
    private VBox createAccountForm;
    private TextField usernameField;
    private PasswordField passwordField;
    private PasswordField newPasswordField;
    private PasswordField confirmPasswordField;
    private TextField emailField; // Add email field for reset
    private final String USER_CREDENTIALS_FILE = "user_credentials.txt"; // File to store credentials

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene galleryScene = new Scene(root, 1100, 600, Color.PINK);

        stage.setScene(galleryScene);
        stage.setTitle("ART GALLERY");
        Image image = new Image(getClass().getResource("/com/example/demo8/Pookie.png").toExternalForm());
        stage.getIcons().add(image);
        stage.setFullScreen(true);

        loginForm = new VBox(10);
        loginForm.setAlignment(Pos.CENTER);
        loginForm.setStyle("-fx-background-color: transparent; -fx-padding: 30px; -fx-border-radius: 30px; -fx-background-radius: 30px;");

        loginForm.layoutXProperty().bind(root.widthProperty().subtract(loginForm.widthProperty()).divide(2));
        loginForm.layoutYProperty().bind(root.heightProperty().subtract(loginForm.heightProperty()).divide(2).add(50));

        BackgroundFill b1 = new BackgroundFill(Color.PINK, null, null);
        Background b2 = new Background(b1);
        root.setBackground(b2);
        Image image1 = new Image(getClass().getResource("/com/example/demo8/img_1.png").toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(
                image1,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        BackgroundSize.AUTO,
                        BackgroundSize.AUTO,
                        false,
                        false,
                        true,
                        true
                )
        );
        Background background = new Background(backgroundImage);
        root.setBackground(background);

        Text text = new Text("WELCOME TO ART GALLERY");
        text.setFont(Font.font("Algerian", 60));
        text.setFill(Color.BLACK);
        text.setX(250);
        text.setY(150);
        DropShadow shadow = new DropShadow();
        text.setEffect(shadow);

        Line line = new Line();
        line.setStartX(250);
        line.setStartY(180);
        line.setEndX(1050);
        line.setEndY(180);
        line.setStrokeWidth(5);

        HBox loginContainer = new HBox(20);
        loginContainer.setAlignment(Pos.CENTER);
        loginContainer.layoutXProperty().bind(root.widthProperty().subtract(loginContainer.widthProperty()).divide(2));
        loginContainer.layoutYProperty().bind(root.heightProperty().subtract(loginContainer.heightProperty()).divide(2).add(50));

        Image loginImage = new Image(getClass().getResource("/com/example/demo8/img_3.png").toExternalForm());
        ImageView loginImageView = new ImageView(loginImage);
        loginImageView.setFitWidth(400);
        loginImageView.setFitHeight(400);
        loginImageView.setPreserveRatio(true);

        loginContainer.getChildren().addAll(loginImageView, loginForm);

        root.getChildren().add(loginContainer);

        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font("Arial", 16));
        usernameLabel.setTextFill(Color.BLACK);
        usernameField = new TextField();
        usernameField.setPromptText("Enter your username");

        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", 16));
        passwordLabel.setTextFill(Color.BLACK);
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        Button loginButton = new Button("Login");
        loginButton.setFont(Font.font("Arial", 14));
        loginButton.setOnAction(e -> handleLogin(stage));
        loginButton.setCursor(Cursor.HAND);

        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
        forgotPasswordLink.setTextFill(Color.BLACK);
        forgotPasswordLink.setOnAction(e -> toggleForms());
        forgotPasswordLink.setCursor(Cursor.HAND);

        Hyperlink createAccountLink = new Hyperlink("Create a New Account");
        createAccountLink.setTextFill(Color.BLACK);
        createAccountLink.setOnAction(e -> showCreateAccountForm());
        createAccountLink.setCursor(Cursor.HAND);

        loginForm.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, forgotPasswordLink, createAccountLink);

        newPasswordForm = new VBox(10);
        newPasswordForm.setAlignment(Pos.CENTER);
        newPasswordForm.setStyle("-fx-background-color: TRANSPARENT; -fx-padding: 20px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        newPasswordForm.setLayoutX(400);
        newPasswordForm.setLayoutY(250);
        newPasswordForm.setVisible(false);
        newPasswordForm.layoutXProperty().bind(root.widthProperty().subtract(newPasswordForm.widthProperty()).divide(2));
        newPasswordForm.layoutYProperty().bind(root.heightProperty().subtract(newPasswordForm.heightProperty()).divide(2).add(50));

        Label emailLabelForReset = new Label("Email:");
        emailLabelForReset.setFont(Font.font("Arial", 16));
        emailLabelForReset.setTextFill(Color.BLACK);
        emailField = new TextField();
        emailField.setPromptText("Enter your email");

        Label newPasswordLabel = new Label("New Password:");
        newPasswordLabel.setFont(Font.font("Arial", 16));
        newPasswordLabel.setTextFill(Color.BLACK);
        newPasswordField = new PasswordField();
        newPasswordField.setPromptText("Enter your new password");

        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordLabel.setFont(Font.font("Arial", 16));
        confirmPasswordLabel.setTextFill(Color.BLACK);
        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Retype your new password");

        Button resetPasswordButton = new Button("Reset Password");
        resetPasswordButton.setFont(Font.font("Arial", 14));
        resetPasswordButton.setOnAction(e -> resetPassword());
        resetPasswordButton.setCursor(Cursor.HAND);

        Button backToLoginButtonFromReset = new Button("Back");
        backToLoginButtonFromReset.setFont(Font.font("Arial", 14));
        backToLoginButtonFromReset.setOnAction(e -> switchToLoginForm());
        backToLoginButtonFromReset.setCursor(Cursor.HAND);

        newPasswordForm.getChildren().addAll(emailLabelForReset, emailField, newPasswordLabel, newPasswordField, confirmPasswordLabel, confirmPasswordField, resetPasswordButton, backToLoginButtonFromReset);

        createAccountForm = new VBox(10);
        createAccountForm.setAlignment(Pos.CENTER);
        createAccountForm.setStyle("-fx-background-color: transparent; -fx-padding: 20px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        createAccountForm.setLayoutX(400);
        createAccountForm.setLayoutY(250);
        createAccountForm.setVisible(false);
        createAccountForm.layoutXProperty().bind(root.widthProperty().subtract(createAccountForm.widthProperty()).divide(2));
        createAccountForm.layoutYProperty().bind(root.heightProperty().subtract(createAccountForm.heightProperty()).divide(2).add(50));

        Label newUsernameLabel = new Label("Username:");
        newUsernameLabel.setFont(Font.font("Arial", 16));
        newUsernameLabel.setTextFill(Color.BLACK);
        TextField newUsernameField = new TextField();
        newUsernameField.setPromptText("Enter your username");

        Label emailLabel = new Label("Email:");
        emailLabel.setFont(Font.font("Arial", 16));
        emailLabel.setTextFill(Color.BLACK);
        TextField emailFieldForAccount = new TextField();
        emailFieldForAccount.setPromptText("Enter your email");

        Label newPasswordLabelForAccount = new Label("Password:");
        newPasswordLabelForAccount.setFont(Font.font("Arial", 16));
        newPasswordLabelForAccount.setTextFill(Color.BLACK);
        PasswordField newPasswordFieldForAccount = new PasswordField();
        newPasswordFieldForAccount.setPromptText("Enter your password");

        Label confirmNewPasswordLabel = new Label("Confirm Password:");
        confirmNewPasswordLabel.setFont(Font.font("Arial", 16));
        confirmNewPasswordLabel.setTextFill(Color.BLACK);
        PasswordField confirmNewPasswordField = new PasswordField();
        confirmNewPasswordField.setPromptText("Retype your password");

        Button createAccountButton = new Button("Create Account");
        createAccountButton.setFont(Font.font("Arial", 14));
        createAccountButton.setOnAction(e -> handleCreateAccount(newUsernameField.getText(), emailFieldForAccount.getText(), newPasswordFieldForAccount.getText(), confirmNewPasswordField.getText()));
        createAccountButton.setCursor(Cursor.HAND);

        Button backToLoginButtonFromAccount = new Button("Back");
        backToLoginButtonFromAccount.setFont(Font.font("Arial", 14));
        backToLoginButtonFromAccount.setOnAction(e -> switchToLoginForm());
        backToLoginButtonFromAccount.setCursor(Cursor.HAND);

        createAccountForm.getChildren().addAll(newUsernameLabel, newUsernameField, emailLabel, emailFieldForAccount, newPasswordLabelForAccount, newPasswordFieldForAccount, confirmNewPasswordLabel, confirmNewPasswordField, createAccountButton, backToLoginButtonFromAccount);

        root.getChildren().addAll(text, line, loginForm, newPasswordForm, createAccountForm);
        stage.show();
    }

    private void handleLogin(Stage stage) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (validateLogin(username, password)) {
            System.out.println("Login successful!");
            openArtworkManagerUI();
            stage.close();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private boolean validateLogin(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_CREDENTIALS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(username) && credentials[2].equals(password)) { // Fixed password validation
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void openArtworkManagerUI() {
        ArtworkManagerUI artworkManagerUI = new ArtworkManagerUI();
        Stage artworkStage = new Stage();
        Scene artworkScene = new Scene(artworkManagerUI, 800, 600);
        artworkStage.setTitle("Artwork Manager");
        artworkStage.setScene(artworkScene);
    }

    private void handleCreateAccount(String username, String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_CREDENTIALS_FILE, true))) {
            writer.write(username + "," + email + "," + password);
            writer.newLine();
            System.out.println("Account created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetPassword() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(USER_CREDENTIALS_FILE))) {
            StringBuilder newFileContent = new StringBuilder();
            String line;
            boolean userFound = false;

            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(username) && credentials[1].equals(email)) {
                    newFileContent.append(username).append(",").append(email).append(",").append(newPassword).append("\n");
                    userFound = true;
                } else {
                    newFileContent.append(line).append("\n");
                }
            }

            if (!userFound) {
                System.out.println("User not found or email does not match.");
                return;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_CREDENTIALS_FILE))) {
                bw.write(newFileContent.toString());
            }
            System.out.println("Password reset successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateAccountForm() {
        loginForm.setVisible(false);
        createAccountForm.setVisible(true);
    }

    private void switchToLoginForm() {
        newPasswordForm.setVisible(false);
        createAccountForm.setVisible(false);
        loginForm.setVisible(true);
    }

    private void toggleForms() {
        loginForm.setVisible(false);
        newPasswordForm.setVisible(true);
    }

    public static void main(String[] args) {
        launch();
    }
}