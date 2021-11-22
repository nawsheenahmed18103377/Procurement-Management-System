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
public class AdminController implements Initializable {
    ObservableList<ProductModel> oblist = FXCollections.observableArrayList();
    private Connection connection;
    private PreparedStatement preparedStatement;
    
    @FXML
    private TableView<ProductModel> table;
    @FXML
    private TableColumn<ProductModel, String> pidCol;
    @FXML
    private TableColumn<ProductModel, String> pnameCol;
    @FXML
    private TableColumn<ProductModel, String> pquantityCol;
    @FXML
    private TableColumn<ProductModel, String> ppriceCol;
    @FXML
    private Button approve;
    @FXML
    private Button decline;
    @FXML
    private Button edit;
    @FXML
    private Button logout;
    @FXML
    private Button approvalList;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            connection = DbConnection.getConnection();
            String sql = "select * from pending";
            ResultSet rs = connection.createStatement().executeQuery(sql);

            while (rs.next()) {
                oblist.add(new ProductModel(rs.getString("pid"), rs.getString("pname"), rs.getString("pquantity"), rs.getString("pprice")));
            }

        } catch (SQLException ex) {
            System.out.print(ex);
        }
        pidCol.setCellValueFactory(new PropertyValueFactory<>("pid"));
        pnameCol.setCellValueFactory(new PropertyValueFactory<>("pname"));
        pquantityCol.setCellValueFactory(new PropertyValueFactory<>("pquantity"));
        ppriceCol.setCellValueFactory(new PropertyValueFactory<>("pprice"));
        
        table.setItems(oblist);
    }    
    
    private void refresh() {
        oblist.clear();
        try {
            connection = DbConnection.getConnection();
            String sql = "select * from pending";
            ResultSet rs = connection.createStatement().executeQuery(sql);

            while (rs.next()) {
                oblist.add(new ProductModel(rs.getString("pid"), rs.getString("pname"), rs.getString("pquantity"), rs.getString("pprice")));
            }

        } catch (SQLException ex) {
            System.out.print(ex);
        }
        pidCol.setCellValueFactory(new PropertyValueFactory<>("pid"));
        pnameCol.setCellValueFactory(new PropertyValueFactory<>("pname"));
        pquantityCol.setCellValueFactory(new PropertyValueFactory<>("pquantity"));
        ppriceCol.setCellValueFactory(new PropertyValueFactory<>("pprice"));
        
        table.setItems(oblist);
    }

    @FXML
    private void approvedAction(ActionEvent event) {
        try {
            ProductModel user = (ProductModel) table.getSelectionModel().getSelectedItem();
            connection = DbConnection.getConnection();
            String sql = "insert into approved  (pid, pname, pquantity, pprice) values (?,?,?,?) ";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getPid());
            preparedStatement.setString(2, user.getPname());
            preparedStatement.setString(3, user.getPquantity());
            preparedStatement.setString(4, user.getPprice());
            preparedStatement.execute();
            
            String query = "DELETE  from pending where pid=?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getPid());
            preparedStatement.executeUpdate();
            
            refresh();
            preparedStatement.close();
                        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void declineAction(ActionEvent event) {
        try {
            ProductModel user = (ProductModel) table.getSelectionModel().getSelectedItem();
            connection = DbConnection.getConnection();
            String query = "DELETE  from pending where pid=?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getPid());
            preparedStatement.executeUpdate();
            
            refresh();
            //success.setText("Works has been Deleted Successfully");
            preparedStatement.close();
                        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void editAction(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Procurement.fxml"));
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
    private void approvalListAction(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("ApprovalList.fxml"));
        Scene home_page_scene = new Scene(home);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
}
