/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incognitocoders;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProcurementController implements Initializable {
    private Connection connection;
    private PreparedStatement preparedStatement;
    
    @FXML
    private Button send;
     @FXML
    private Button back;
    @FXML
    private Button approvalList;
    @FXML
    private Label success;
    @FXML
    private Button logout;
    @FXML
    private TextField txtpid;
    @FXML
    private TextField txtpname;
    @FXML
    private TextField txtpquantity;
    @FXML
    private TextField txtpprice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendAction(ActionEvent event) {
        try {
            connection = DbConnection.getConnection();

            String sql = "insert into pending  (pname, pquantity, pprice) values (?,?,?) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, txtpname.getText());
            preparedStatement.setString(2, txtpquantity.getText());
            preparedStatement.setString(3, txtpprice.getText());
            preparedStatement.execute();
            success.setText("New Request Send Successfully");

            preparedStatement.close();

            txtpid.clear();
            txtpname.clear();
            txtpquantity.clear();
            txtpprice.clear();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void approvalListAction(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("ApprovalList.fxml"));
        Scene home_page_scene = new Scene(home);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void logoutAction(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene home_page_scene = new Scene(home);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
       @FXML
    private void backAction(ActionEvent event) throws IOException {
        Parent admin = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene home_page_scene = new Scene(admin);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
}
