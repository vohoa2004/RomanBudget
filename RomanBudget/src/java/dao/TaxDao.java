/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Tax;
import model.TaxCategory;
import utils.DBContext;

/**
 *
 * @author vothimaihoa
 */
public class TaxDao extends DBContext {

    public List<Tax> getAll() {
        List<Tax> taxList = new ArrayList<>();

        String sql = "SELECT t.*, tc.name, tc.unit_price\n"
                + "FROM Tax t inner join Tax_Category tc\n"
                + "ON t.tax_category_id = tc.id";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Tax tax = new Tax();
                tax.setId(resultSet.getInt("id"));
                tax.setRegionId(resultSet.getInt("region_id"));
                tax.setTaxAmount(resultSet.getDouble("tax_amount"));
                tax.setTributeAmount(resultSet.getDouble("tribute_amount"));
                tax.setDeadline(resultSet.getDate("deadline"));
                tax.setPaidDate(resultSet.getDate("paid_date"));
                tax.setYear(resultSet.getInt("year"));
                TaxCategory tc = new TaxCategory();
                tc.setId(resultSet.getInt("tax_category_id"));
                tc.setName(resultSet.getString("name"));
                tc.setUnitPrice(resultSet.getDouble("unit_price"));
                tax.setCategory(tc);

                taxList.add(tax);
            }
        } catch (SQLException e) {
            System.out.println(e);; // Xử lý hoặc báo lỗi nếu cần
        }

        return taxList;
    }

    public List<Tax> getByRegionId(int regionId) {
        List<Tax> taxList = new ArrayList<>();

        String sql = "SELECT t.*, tc.name, tc.unit_price\n"
                + "FROM Tax t inner join Tax_Category tc\n"
                + "ON t.tax_category_id = tc.id "
                + "where region_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, regionId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Tax tax = new Tax();
                tax.setId(resultSet.getInt("id"));
                tax.setRegionId(resultSet.getInt("region_id"));
                tax.setTaxAmount(resultSet.getDouble("tax_amount"));
                tax.setTributeAmount(resultSet.getDouble("tribute_amount"));
                tax.setDeadline(resultSet.getDate("deadline"));
                tax.setPaidDate(resultSet.getDate("paid_date"));
                tax.setYear(resultSet.getInt("year"));
                TaxCategory tc = new TaxCategory();
                tc.setId(resultSet.getInt("tax_category_id"));
                tc.setName(resultSet.getString("name"));
                tc.setUnitPrice(resultSet.getDouble("unit_price"));
                tax.setCategory(tc);

                taxList.add(tax);
            }
        } catch (SQLException e) {
            System.out.println(e);; // Xử lý hoặc báo lỗi nếu cần
        }

        return taxList;
    }

    public void insert(Tax newTax) {
        String sql = "insert into Tax values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // Set các tham số vào query để insert
            st.setInt(1, newTax.getRegionId());
            st.setDouble(2, newTax.getTaxAmount());
            st.setDouble(3, newTax.getTributeAmount());
            st.setDate(4, newTax.getDeadline());
            st.setDate(5, newTax.getPaidDate());
            st.setInt(6, newTax.getCategory().getId());
            st.setInt(7, newTax.getYear());

            st.executeUpdate();
            System.out.println("Inserted!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void update(Date paidDate, int id) {
        String sql = "update Tax set paid_date = ? where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // Set các tham số vào query để insert
            st.setDate(1, paidDate);
            st.setInt(2, id);
            st.executeUpdate();
            System.out.println("Updated!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<Tax> getListByPage(List<Tax> list,
            int start, int end) {
        List<Tax> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public static void main(String[] args) {
        TaxDao td = new TaxDao();
        List<Tax> list = td.getByRegionId(1);
        for (Tax t : list) {
            System.out.println(t.toString());
        }
//        Tax newTax = new Tax(2, 1, 1, new Date(2023, 12, 31));
//        td.insert(newTax);
    Date date = new Date(2023, 12, 31);
        td.update(date, 2);
        for (Tax t : list) {
            System.out.println(t.toString());
        }
    }

}
