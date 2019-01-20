package ProjectDatabases;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TextFieldValidation {
    public static boolean isTextFieldNotEmpty(TextField tf){
            boolean b = false;
            if(tf.getText().length() != 0 || !tf.getText().isEmpty())
            {
                b = true;
            }
            return b;
    }
    public static boolean isDateNotEmpty(DatePicker datePicker){
        boolean bd = false;
        if(datePicker.getValue() != null)
        {
            bd = true;
        }
        return bd;
    }
    public static boolean isTextFieldNotEmpty(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!isTextFieldNotEmpty(tf))
        {
            msg =  errorMessage;
            b = false;
        }
        lb.setText(msg);
        return b;
    }
    public static boolean isDateNotEmpty(DatePicker dp, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!isDateNotEmpty(dp))
        {
            msg =  errorMessage;
            b = false;
        }
        lb.setText(msg);
        return b;
    }
    public static boolean isTextFieldTypeNumber(TextField tf){
        boolean b = false;

        if(tf.getText().matches("([0-9] + (\\.[0-9]+)?)+")){
            b = true;
        }
        return b;
    }
    public static boolean isTextFieldTypeNumber(TextField tf,Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        if(!isTextFieldTypeNumber(tf)){
            b = true;
            msg = errorMessage;
        }
        lb.setText(msg);
        return b;
    }
}
