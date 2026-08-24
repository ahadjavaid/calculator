package com.calculator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class CalculatorFXApplication extends Application {

    private static ApplicationContext context;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/calculator-view.fxml"));

        loader.setControllerFactory(context::getBean);

        Parent root = loader.load();

        Scene scene = new Scene(root, 350, 400);

        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        context = new SpringApplicationBuilder(CalculatorApplication.class).run(args);
        launch(args);
    }
}
