module presentation.javafxexample {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens presentation.javafxexample to javafx.fxml;
    exports presentation.javafxexample;
}