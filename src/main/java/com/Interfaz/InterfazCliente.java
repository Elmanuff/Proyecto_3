package com.Interfaz;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;

public class InterfazCliente  extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login and Registration");

        VBox vbox = new VBox(10); // 10 pixels de espacio entre los elementos
        vbox.setPadding(new Insets(20, 20, 20, 20)); // Padding alrededor del VBox
        vbox.setAlignment(Pos.CENTER); // Centrar el VBox en la pantalla
        vbox.setStyle("-fx-background-color: #b3c6ff;");
        // Etiquetas
        Label usernameLabel = new Label("Usuario:");
        Label passwordLabel = new Label("Codigo de empleado:");

        // Campos de entrada
        TextField usernameInput = new TextField();
        PasswordField passwordInput = new PasswordField();

        // Botones
        Button inicioButton = new Button("Iniciar sesión");
        Button registroButton = new Button("Registrar");

        // Eventos de botones
        inicioButton.setOnAction(e -> {
            // Lógica de inicio de sesión
            String usuario = usernameInput.getText();
            String contraseña = passwordInput.getText();
            // Aquí deberías verificar las credenciales en tu aplicación
            abrirVentanaCiudad();

        });

        registroButton.setOnAction(e -> {
            abrirVentanaRegistro();
        });

        vbox.getChildren().addAll(usernameLabel, usernameInput, passwordLabel, passwordInput, inicioButton, registroButton);

        Scene mainScene = new Scene(vbox, 300, 400);
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }

    private void abrirVentanaRegistro() {
        primaryStage.hide();
        Stage registrationStage = new Stage();
        registrationStage.setTitle("Registration");

        VBox registrationVbox = new VBox(10);
        registrationVbox.setPadding(new Insets(20, 20, 20, 20));
        registrationVbox.setAlignment(Pos.CENTER);
        registrationVbox.setStyle("-fx-background-color: #b3c6ff;");

        Label nombreLabel = new Label("Nombre:");
        Label codigoEmpleadoLabel = new Label("Código de empleado:");
        Label ubicacionLabel = new Label("Ubicación:");

        TextField nombreInput = new TextField();
        TextField codigoEmpleadoInput = new TextField();
        TextField ubicacionInput = new TextField();

        Button registrarButton = new Button("Registrar");
        Button volerButton = new Button("Volver al inicio");

        registrarButton.setOnAction(e -> {
            // Lógica para registrar el nuevo usuario
            String name = nombreInput.getText();
            String employeeCode = codigoEmpleadoInput.getText();
            String location = ubicacionInput.getText();
            System.out.println("Registration: Name = " + name + ", Code = " + employeeCode + ", Location = " + location);
            registrationStage.close();
            primaryStage.show();
        });

        volerButton.setOnAction(e -> {
            primaryStage.show();
            registrationStage.close();
        });

        registrationVbox.getChildren().addAll(nombreLabel, nombreInput, codigoEmpleadoLabel, codigoEmpleadoInput, ubicacionLabel, ubicacionInput, registrarButton, volerButton);

        Scene registrationScene = new Scene(registrationVbox, 300, 400);
        registrationStage.setScene(registrationScene);

        registrationStage.show();
    }
    private void abrirVentanaCiudad() {
        // Ocultar la ventana principal
        primaryStage.hide();

        // Crear una nueva ventana de simulación de ciudad
        Stage cityStage = new Stage();
        cityStage.setTitle("City Simulation");
        cityStage.initModality(Modality.APPLICATION_MODAL);
        cityStage.setResizable(false);

        // Crear un lienzo para dibujar la ciudad
        Canvas canvas = new Canvas(300, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Dibujar cuadriculas que simulen ser cuadras
        double cellSize = 30;
        int rows = (int) (canvas.getHeight() / cellSize);
        int cols = (int) (canvas.getWidth() / cellSize);

        gc.setFill(Color.LIGHTBLUE);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gc.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }

        // Dibujar líneas que simulen ser calles
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);
        for (int i = 1; i < rows; i++) {
            gc.strokeLine(0, i * cellSize, canvas.getWidth(), i * cellSize);
        }
        for (int j = 1; j < cols; j++) {
            gc.strokeLine(j * cellSize, 0, j * cellSize, canvas.getHeight());
        }

        // Crear un diseño de Pane y agregar el lienzo al centro
        Pane rootPane = new Pane();
        rootPane.getChildren().add(canvas);

        HBox buttonBox = new HBox();
        buttonBox.setPrefHeight(40); // Altura preferida para el contenedor de los botones
        buttonBox.setStyle("-fx-background-color: transparent;"); // Fondo transparente

        Button carpoolingButton = new Button("Activar Carpooling");
        carpoolingButton.setOnAction(e -> {
            // Lógica para activar carpooling
            System.out.println("Carpooling activado");
        });

        // Crear un botón para volver a la pantalla de inicio
        Button backButton = new Button("Volver");
        backButton.setOnAction(e -> {
            // Mostrar nuevamente la ventana principal
            primaryStage.show();
            cityStage.close();
        });

        // Agregar los botones al contenedor HBox
        buttonBox.getChildren().addAll(backButton, carpoolingButton);

        // Posicionar los botones en la parte inferior del contenedor
        HBox.setHgrow(backButton, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(carpoolingButton, javafx.scene.layout.Priority.ALWAYS);

        // Agregar el contenedor HBox encima del Canvas en el Pane
        rootPane.getChildren().add(buttonBox);

        // Ajustar la posición del HBox para que esté en la parte inferior
        buttonBox.setLayoutX(68);
        buttonBox.setLayoutY(370);


        // Crear una escena y establecerla en la ventana de simulación de ciudad
        Scene cityScene = new Scene(rootPane, 300, 400);
        cityStage.setScene(cityScene);

        // Mostrar la ventana de simulación de ciudad
        cityStage.showAndWait();
    }
}

