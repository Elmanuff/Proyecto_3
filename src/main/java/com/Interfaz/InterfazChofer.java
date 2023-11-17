package com.Interfaz;

import com.Cliente.MainCliente;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InterfazChofer extends Application {
    private MainCliente cliente;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Funcion para iniciar la ventana del empleado
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login and Registration");

        VBox vbox = new VBox(10); // 10 pixels de espacio entre los elementos
        vbox.setPadding(new Insets(20, 20, 20, 20)); // Padding alrededor del VBox
        vbox.setAlignment(Pos.CENTER); // Centrar el VBox en la pantalla
        vbox.setStyle("-fx-background-color: #B785EC;");
        // Etiquetas
        Label titulo = new Label("DriverApp");
        titulo.setStyle("-fx-font-family: Arial; -fx-font-size: 20; -fx-text-fill: #7400F0;");
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

        vbox.getChildren().addAll(titulo,usernameLabel, usernameInput, passwordLabel, passwordInput, inicioButton, registroButton);

        Scene mainScene = new Scene(vbox, 300, 400);
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }

    /**
     * Metodo que crea la ventana de registro
     */
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
            String nombre = nombreInput.getText();
            String codigoEmpleado = codigoEmpleadoInput.getText();
            String ubicacion = ubicacionInput.getText();

            System.out.println("Registration: Name = " + nombre + ", Code = " + codigoEmpleado + ", Location = " + ubicacion);
            //Envia al cliente el usuario y lo registra
            //cliente.enviarUsuario(nombre, codigoEmpleado,ubicacion);

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

    /**
     * Metodo que crea la interfaz del mapa
     */
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

        Button addFriendButton = new Button("Agregar amigo");
        addFriendButton.setOnAction(e -> {
            // Lista para almacenar amigos
            ListView<String> friendListView = new ListView<>();

            // Campos de texto para ingresar el nombre y el código del amigo
            TextField friendNameInput = new TextField();
            friendNameInput.setPromptText("Ingrese el nombre del amigo");
            TextField friendCodeInput = new TextField();
            friendCodeInput.setPromptText("Ingrese el código del amigo");

            // Botón para agregar amigos a la lista
            Button addFriend = new Button("Agregar Amigo");
            addFriend.setOnAction(a -> {
                String friendName = friendNameInput.getText();
                String friendCode = friendCodeInput.getText();
                if (!friendName.isEmpty() && !friendCode.isEmpty()) {
                    friendListView.getItems().add(friendName + " - Código: " + friendCode);
                    friendNameInput.clear();
                    friendCodeInput.clear();
                }
            });

            // Diseño de la interfaz utilizando GridPane para organizar los elementos
            GridPane root = new GridPane();
            root.setPadding(new Insets(10));
            root.setHgap(10);
            root.setVgap(10);
            root.addRow(0, friendNameInput, friendCodeInput, addFriend);
            root.addRow(1, friendListView);

            // Configurar la escena y mostrar la ventana
            Scene scene = new Scene(root, 400, 300);
            primaryStage.setTitle("Agregar Amigos");
            primaryStage.setScene(scene);
            primaryStage.show();
        });

        // Crear un botón para volver a la pantalla de inicio
        Button backButton = new Button("Volver");
        backButton.setOnAction(e -> {
            // Mostrar nuevamente la ventana principal
            primaryStage.show();
            cityStage.close();
        });

        // Agregar los botones al contenedor HBox
        buttonBox.getChildren().addAll(backButton, carpoolingButton, addFriendButton);

        // Posicionar los botones en la parte inferior del contenedor
        HBox.setHgrow(backButton, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(carpoolingButton, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(addFriendButton, javafx.scene.layout.Priority.ALWAYS);

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

