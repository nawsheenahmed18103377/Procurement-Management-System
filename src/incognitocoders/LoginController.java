/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incognitocoders;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginController implements Initializable {
    private static void showAlert(String login_Successful) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    ObservableList list = FXCollections.observableArrayList();
        
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private Button login;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbox();
    }    

    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        try {
            Connection conn = DbConnection.getConnection();
            String sql = "select * from users where username='" + username.getText() + "' and password = '"+ password.getText()+"' and type = '"+ type.getValue()+"'";

            ResultSet rs = conn.createStatement().executeQuery(sql);
            if(rs.next()){
                Parent home_page_parent;
                if("Admin".equals(type.getValue()))
                    home_page_parent = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                else 
                    home_page_parent = FXMLLoader.load(getClass().getResource("Procurement.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
            }
            else {
                showAlert("Email or password do not match");
            }
                
        } catch (SQLException ex) {
                ex.printStackTrace();
        }
    }
    private void cbox()
    {
        list.removeAll(list);
        String a="Admin";
        String b="User";
        list.addAll(a, b);
        type.getItems().addAll(list);
    }
}
