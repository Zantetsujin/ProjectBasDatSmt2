module com.example.project_basdat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.project_basdat to javafx.fxml;
    exports com.example.project_basdat;
}