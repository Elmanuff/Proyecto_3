module com.example.proyecto_3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.Interfaz to javafx.graphics;

    exports com.Interfaz;
}