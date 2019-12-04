module Presentation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens Presentation to javafx.fxml;
    exports Presentation;
}