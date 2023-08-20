/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment.mvc.controller;


import assignment.mvc.db.DBConnection;
import assignment.mvc.model.CustomerModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;


/**
 *
 * @author user
 */
public class CustomerController {
    public String saveCustomer(CustomerModel customer)throws SQLException{
        Connection connection= DBConnection.getInstance().getConnection();
        String quary ="INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement =connection.prepareStatement(quary);
        preparedStatement.setString(1, customer.getCustId());
        preparedStatement.setString(2, customer.getTitle());
        preparedStatement.setString(3, customer.getName());
        preparedStatement.setString(4, customer.getDob());
        preparedStatement.setDouble(5, customer.getSalory());
        preparedStatement.setString(6, customer.getAddress());
        preparedStatement.setString(7, customer.getCity());
        preparedStatement.setString(8, customer.getProvince());
        preparedStatement.setString(9, customer.getZip());
        
        if (preparedStatement.executeLargeUpdate()>0){
            return "Success";
        }else{
            return "Fail";
        } 
    }
    public ArrayList<CustomerModel> getAllCustomer() throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Customer";
        PreparedStatement statement = connection.prepareStatement(query);
        
        ArrayList<CustomerModel> customerModels = new ArrayList<>();
        
        ResultSet rst = statement.executeQuery();
        while (rst.next()) {            
            CustomerModel cm = new CustomerModel(rst.getString(1),
                    rst.getString(2), 
                    rst.getString(3), 
                    rst.getString(4), 
                    rst.getDouble(5),
                    rst.getString(6), 
                    rst.getString(7), 
                    rst.getString(8), 
                    rst.getString(9));
            
            customerModels.add(cm);
        }
        
        return customerModels;
    }
    public CustomerModel getCustomer(String custId)throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Customer WHERE CustID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, custId);
        
        ResultSet rst = statement.executeQuery();
        while (rst.next()) {            
            CustomerModel cm = new CustomerModel(rst.getString(1),
                    rst.getString(2), 
                    rst.getString(3), 
                    rst.getString(4), 
                    rst.getDouble(5),
                    rst.getString(6), 
                    rst.getString(7), 
                    rst.getString(8), 
                    rst.getString(9));
            
            return cm;
        }
        return null;
    }
}
