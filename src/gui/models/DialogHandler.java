package gui.models;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class DialogHandler {
    public static boolean confirmationAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, text, new ButtonType[]{ButtonType.YES, ButtonType.NO});
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.YES;
    }

    public static void informationAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, text, new ButtonType[]{ButtonType.OK});
        alert.show();
    }

    public static String inputDialog(String text){
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText(text);
        Optional<String> result = inputDialog.showAndWait();
        return result.orElse("");
    }
}
