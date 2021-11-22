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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ApprovalListController implements Initializable {
    @FXML
    private Button logout;
    @FXML
    private Button back;
    @FXML
    private TableColumn<ProductModel, String> apid;
    @FXML
    private TableColumn<ProductModel, String> apname;
    @FXML
    private TableColumn<ProductModel, String> apquantity;
    @FXML
    private TableColumn<ProductModel, String> apprice;
    ObservableList<ProductModel> data = FXCollections.observableArrayList();
    @FXML
    private TableView<ProductModel> tableView;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = DbConnection.getConnection();
            String sql = "select * from approved";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                System.out.println("Hello World");
                data.add(new ProductModel(rs.getString("pid"), rs.getString("pname"), rs.getString("pquantity"), rs.getString("pprice")
                ));
            }

        } catch (SQLException ex) {
            System.out.print(ex);
        }
        apid.setCellValueFactory(new PropertyValueFactory<>("pid"));
        apname.setCellValueFactory(new PropertyValueFactory<>("pname"));
        apquantity.setCellValueFactory(new PropertyValueFactory<>("pquantity"));
        apprice.setCellValueFactory(new PropertyValueFactory<>("pprice"));
        tableView.setItems(data);
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
        Parent test = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene home_page_scene = new Scene(test);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
}
