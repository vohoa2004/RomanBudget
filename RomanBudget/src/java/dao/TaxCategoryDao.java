/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.TaxCategory;
import utils.DBContext;

/**
 *
 * @author vothimaihoa
 */
public class TaxCategoryDao extends DBContext{
    public List<TaxCategory> getAll() {
        List<TaxCategory> taxCatList = new ArrayList<>();

        String sql = "SELECT * FROM Tax_Category";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TaxCategory taxCat = new TaxCategory();
                taxCat.setId(resultSet.getInt("id"));
                taxCat.setName(resultSet.getString("name"));
                taxCat.setUnitPrice(resultSet.getDouble("unit_price"));
                taxCatList.add(taxCat);
            }
        } catch (SQLException e) {
            System.out.println(e);; // Xử lý hoặc báo lỗi nếu cần
        }

        return taxCatList;
    }
    
    public TaxCategory getById(int id) {
        TaxCategory result = null;

        String sql = "SELECT * FROM Tax_Category where id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                TaxCategory taxCat = new TaxCategory();
                taxCat.setId(resultSet.getInt("id"));
                taxCat.setName(resultSet.getString("name"));
                taxCat.setUnitPrice(resultSet.getDouble("unit_price"));
                result = taxCat;
            }
        } catch (SQLException e) {
            System.out.println(e);; // Xử lý hoặc báo lỗi nếu cần
        }

        return result;
    }
    
    
    public static void main(String[] args) {
        TaxCategoryDao td = new TaxCategoryDao();
        List<TaxCategory> list = td.getAll();
        for (TaxCategory t : list) {
            System.out.println(t.toString());
        }
        TaxCategory tc = td.getById(2);
        System.out.println(tc.toString());
//        Tax newTax = new Tax(2, 1, 1, new Date(31, 12, 2023));
//        td.insert(newTax);
    }
    
}
