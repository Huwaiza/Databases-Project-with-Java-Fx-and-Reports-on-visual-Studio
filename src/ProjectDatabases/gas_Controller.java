package ProjectDatabases;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class gas_Controller implements Initializable {
    //Add
    private BorderPane bd = new BorderPane();
    @FXML
    private TextField amount_gas;
    @FXML
    private DatePicker bill_gas_date;
    @FXML
    private TextField fine_gas;
    @FXML
    private  TextField bill_id;
    @FXML
    private  TextField search_gas;
    @FXML
    private Button btn_delete_gas;
    //View
    @FXML
    private TableView<GasBillList> tblGas;
    @FXML
    private TableColumn<? , ?> cBillId;
    @FXML
    private TableColumn<?,?> cBillDate;
    @FXML
    private TableColumn<?, ?> cBillFine;
    @FXML
    private TableColumn<?, ?> cBillAmount;
    //Error check
    @FXML
    private Label err_date;
    @FXML
    private Label err_amount;
    @FXML
    private Label err_fine;
    @FXML
    private Label idshow;
    @FXML
    private Label isselected;
    @FXML
    private Label total_bill;
    @FXML
    private Label total_fine;

    myConnection mycon = new myConnection();
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<GasBillList> data;
    @FXML
    private void handleDelete(ActionEvent event)
    {
        String id = String.valueOf(idshow.getText());
        bill_id.setPromptText(id+" is currently selected");
        String sql = "delete from tbl_gas where gas_Id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,bill_id.getText());

            int i=  pst.executeUpdate();
            if(i==1)
            {
                AlertDialogs.display("Deleted","Bill is deleted successfully");
                loadDataFromDatabase();
                textFieldClear();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        totalBillAndFine();
    }
    @FXML
    private void searcBillFromId(){
        search_gas.setOnKeyReleased(e->{
            if(search_gas.getText().equals(""))
            {
                loadDataFromDatabase();
            }
            else
            {
                data.clear();
                String sql = "Select * from tbl_gas where gas_Id LIKE '%" + search_gas.getText() + "%' ";

//                String sql = "Select * from tbl_gas where gas_Id LIKE '%" + search_gas.getText() + "%' "
//                        +"UNION Select * from tbl_gas where bill_date LIKE '%" + bill_gas_date.getValue() + "%'" ;

                try {
                    pst = con.prepareStatement(sql);
                    //pst.setString(1,);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        data.add(new GasBillList("" + rs.getInt(1), "" + rs.getDouble(3), "" + rs.getDate(4), "" + rs.getDouble(2)));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                tblGas.setItems(data);
            }
        });
    }
    @FXML
    private void handleUpdate(ActionEvent event) throws Exception{
        boolean isDateEmpty = TextFieldValidation.isDateNotEmpty(bill_gas_date,err_date,"Date Error");
        boolean isAmountEmpty = TextFieldValidation.isTextFieldNotEmpty(amount_gas,err_amount,"Amount Error");
        boolean isFineEmpty = TextFieldValidation.isTextFieldNotEmpty(fine_gas,err_fine,"Fine Error");

        if (isAmountEmpty && isDateEmpty && isFineEmpty) {
            String sql = "Update tbl_gas set amount=?, billFine=? ,bill_date=? where gas_Id=?";
            try {
                double amount = Double.valueOf(amount_gas.getText());
                double fine = Double.valueOf(fine_gas.getText());
                String date = bill_gas_date.getEditor().getText();
                String id = String.valueOf(idshow.getText());
                if (idshow.getText().length() == 0 || idshow.getText().isEmpty()) {
                    isselected.setText("Select id");
                }

                pst = con.prepareStatement(sql);
                pst.setDouble(1, amount);
                pst.setDouble(2, fine);
                pst.setString(3, date);
                pst.setString(4, id);

                int i = pst.executeUpdate();
                if (i == 1) {
                    AlertDialogs.display("Update", "INTERNET BILL Updated successfully on id " + id);//System.out.println("Data inserted successfuly");
                    textFieldClear();
                    loadDataFromDatabase();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        totalBillAndFine();
    }
    @FXML
    private void setCellValueOnClickToTextField(){
        tblGas.setOnMouseClicked(event -> {
            GasBillList ibl = tblGas.getItems().get(tblGas.getSelectionModel().getSelectedIndex());
            bill_gas_date.setPromptText(ibl.getBillDate());
            amount_gas.setText(ibl.getAmount());
            fine_gas.setText(ibl.getBillFine());
            idshow.setText(ibl.getBillId());
            isselected.setText("Is selected");
        });
    }
    @FXML
    private void handleSubmit(ActionEvent event) throws SQLException {
        boolean isDateEmpty = TextFieldValidation.isDateNotEmpty(bill_gas_date,err_date,"Date Error");
        boolean isAmounterror = TextFieldValidation.isTextFieldTypeNumber(amount_gas,err_amount,"");
        boolean isAmountEmpty = TextFieldValidation.isTextFieldNotEmpty(amount_gas,err_amount,"Amount Error");
        boolean isFineerror = TextFieldValidation.isTextFieldTypeNumber(fine_gas,err_fine,"");
        boolean isFineEmpty = TextFieldValidation.isTextFieldNotEmpty(fine_gas,err_fine,"Fine Error");
        isselected.setText(null);
        idshow.setText(null);
        if(isDateEmpty && isAmountEmpty && isFineEmpty && isAmounterror && isFineerror) {
            String sql = "insert into tbl_gas(amount,billFine,bill_date) values(?,?,?)";
            double amount = Double.valueOf(amount_gas.getText());
            double fine = Double.valueOf(fine_gas.getText());

            //Date dateOfBill = Date.valueOf(bill_gas_date.getValue());
            String datee = bill_gas_date.getEditor().getText();
            //        String delButton = btn_delete_gas.getText();

            try {
                pst = con.prepareStatement(sql);

                pst.setDouble(1, amount);
                pst.setDouble(2, fine);
                pst.setString(3, datee);


                int i = pst.executeUpdate();
                if (i == 1) {
                    AlertDialogs.display("BILL", "INTERNET BILL insterted successfully");//System.out.println("Data inserted successfuly");
                    setCellTable();
                    loadDataFromDatabase();
                    textFieldClear();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                pst.close();
            }
        }
        totalBillAndFine();
    }

    public void initialize(URL Location, ResourceBundle resources) {
        assert this.bd != null : "fx:id=\"gas\" was not injected: check your FXML file 'Gas.fxml'.";
        con = mycon.conn;
        data = FXCollections.observableArrayList();
        setCellTable();
        loadDataFromDatabase();
        setCellValueOnClickToTextField();
        searcBillFromId();
        totalBillAndFine();
    }
    private void setCellTable(){
        cBillId.setCellValueFactory(new PropertyValueFactory<>("BillId"));
        cBillDate.setCellValueFactory(new PropertyValueFactory<>("BillDate"));
        cBillFine.setCellValueFactory(new PropertyValueFactory<>("BillFine"));
        cBillAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
    }
    private void  totalBillAndFine(){
        try {
            String sql = "select sum(amount) from tbl_gas ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next())
            {
                Double sum1= rs.getDouble(1);
                String sum = String.valueOf(sum1);
                total_bill.setText(sum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String sql = "select sum(billFine) from tbl_gas ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next())
            {
                Double sum1= rs.getDouble(1);
                String sum = String.valueOf(sum1);
                total_fine.setText(sum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void loadDataFromDatabase(){
        data.clear();
        try {
            pst = con.prepareStatement("select * from tbl_gas");
            rs = pst.executeQuery();
            while (rs.next())
            {
                data.add(new GasBillList(""+rs.getInt(1), ""+rs.getDouble(3),""+rs.getDate(4) ,"" +rs.getDouble(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tblGas.setItems(data);
    }
    private void textFieldClear(){
        amount_gas.clear();
        fine_gas.clear();
    }
    @FXML
    private void Water() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("Water.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Water");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @FXML
    private void phone() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("phone.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setTitle("phone");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @FXML
    private void internet() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("internet.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setTitle("phone");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @FXML
    private void electricity() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("electricity.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Electricity");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}