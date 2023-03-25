module com.example.quixo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.quixo to javafx.fxml;
    exports com.example.quixo;
    exports com.example.quixo.Controller;
    opens com.example.quixo.Controller to javafx.fxml;
    exports com.example.quixo.Model;
    opens com.example.quixo.Model to javafx.fxml;


}