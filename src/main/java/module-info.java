module com.example.proyecto_3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyecto_3 to javafx.fxml;
    opens com.Interfaz to javafx.graphics;

    exports com.example.proyecto_3;
    exports com.Interfaz;
}