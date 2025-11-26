module me.evankim.cs4270final {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.bytedeco.opencv;
    requires org.bytedeco.javacv;


    opens me.evankim.cs4270final to javafx.fxml;
    exports me.evankim.cs4270final;
}