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

public class electricity_Controller implements Initializable {
    private BorderPane bd = new BorderPane();
    @FXML
    private TextField amount_electricty;
    @FXML
    private DatePicker bill_electricity_date;
    @FXML
    private TextField fine_electricity;
    @FXML
    private  TextField bill_id;
    @FXML
    private  TextField search_electricity;
    @FXML
    private Button btn_delete_electricity;
    //View
    @FXML
    private TableView<ElectricityBillList> tblelectricity;
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
    private ObservableList<ElectricityBillList> data;
    @FXML
    private void handleDelete(ActionEvent event)
    {
        String id = String.valueOf(idshow.getText());
        bill_id.setPromptText(id+" is currently selected");
        String sql = "delete from tbl_electricity where elec_Id=?";
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
        search_electricity.setOnKeyReleased(e->{
            if(search_electricity.getText().equals(""))
            {
                loadDataFromDatabase();
            }
            else
            {
                data.clear();
                String sql = "Select * from tbl_electricity where elec_Id LIKE '%" + search_electricity.getText() + "%' ";

                try {
                    pst = con.prepareStatement(sql);
                    //pst.setString(1,);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        data.add(new ElectricityBillList("" + rs.getInt(1), "" + rs.getDouble(3), "" + rs.getDate(4), "" + rs.getDouble(2)));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                tblelectricity.setItems(data);
            }
        });
    }
    @FXML
    private void handleUpdate(ActionEvent event) throws Exception{
        boolean isDateEmpty = TextFieldValidation.isDateNotEmpty(bill_electricity_date,err_date,"Date Error");
        boolean isAmountEmpty = TextFieldValidation.isTextFieldNotEmpty(amount_electricty,err_amount,"Amount Error");
        boolean isFineEmpty = TextFieldValidation.isTextFieldNotEmpty(fine_electricity,err_fine,"Fine Error");

        if (isAmountEmpty && isDateEmpty && isFineEmpty) {
            String sql = "Update tbl_electricity set amount=?, billFine=? ,bill_date=? where elec_Id=?";
            try {
                double amount = Double.valueOf(amount_electricty.getText());
                double fine = Double.valueOf(fine_electricity.getText());
                String date = bill_electricity_date.getEditor().getText();
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
                    AlertDialogs.display("Update", "Electricity BILL Updated successfully on id " + id);
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
        tblelectricity.setOnMouseClicked(event -> {
            ElectricityBillList ibl = tblelectricity.getItems().get(tblelectricity.getSelectionModel().getSelectedIndex());
            bill_electricity_date.setPromptText(ibl.getBillDate());
            amount_electricty.setText(ibl.getAmount());
            fine_electricity.setText(ibl.getBillFine());
            idshow.setText(ibl.getBillId());
            isselected.setText("Is selected");
        });
    }
    @FXML
    private void handleSubmit(ActionEvent event) throws SQLException {
        boolean isDateEmpty = TextFieldValidation.isDateNotEmpty(bill_electricity_date,err_date,"Date Error");
        boolean isAmounterror = TextFieldValidation.isTextFieldTypeNumber(amount_electricty,err_amount,"");
        boolean isAmountEmpty = TextFieldValidation.isTextFieldNotEmpty(amount_electricty,err_amount,"Amount Error");
        boolean isFineerror = TextFieldValidation.isTextFieldTypeNumber(fine_electricity,err_fine,"");
        boolean isFineEmpty = TextFieldValidation.isTextFieldNotEmpty(fine_electricity,err_fine,"Fine Error");
        isselected.setText(null);
        idshow.setText(null);
        if(isDateEmpty && isAmountEmpty && isFineEmpty && isAmounterror && isFineerror) {
            String sql = "insert into tbl_electricity(amount,billFine,bill_date) values(?,?,?)";
            double amount = Double.valueOf(amount_electricty.getText());
            double fine = Double.valueOf(fine_electricity.getText());

            //Date dateOfBill = Date.valueOf(bill_electricity_date.getValue());
            String datee = bill_electricity_date.getEditor().getText();
            //        String delButton = btn_delete_electricity.getText();

            try {
                pst = con.prepareStatement(sql);

                pst.setDouble(1, amount);
                pst.setDouble(2, fine);
                pst.setString(3, datee);


                int i = pst.executeUpdate();
                if (i == 1) {
                    AlertDialogs.display("BILL", "Electricity BILL insterted successfully");
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
        assert this.bd != null : "fx:id=\"Electricity\" was not injected: check your FXML file 'electricity.fxml'.";
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
            String sql = "select sum(amount) from tbl_electricity ";
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
            String sql = "select sum(billFine) from tbl_electricity ";
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
            pst = con.prepareStatement("select * from tbl_electricity");
            rs = pst.executeQuery();
            while (rs.next())
            {
                data.add(new ElectricityBillList(""+rs.getInt(1), ""+rs.getDouble(3),""+rs.getDate(4) ,"" +rs.getDouble(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tblelectricity.setItems(data);
    }
    private void textFieldClear(){
        amount_electricty.clear();
        fine_electricity.clear();
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
    private void gas() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("Gas.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Gas");
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


}
