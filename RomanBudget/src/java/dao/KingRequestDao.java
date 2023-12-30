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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.KingRequest;
import utils.DBContext;

/**
 *
 * @author vothimaihoa
 */
public class KingRequestDao extends DBContext{
    public List<KingRequest> getAll() {
        List<KingRequest> kingRequests = new ArrayList<>();
        String sql = "SELECT * FROM King_Request";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                KingRequest kingRequest = new KingRequest();
                kingRequest.setId(resultSet.getInt("id"));
                kingRequest.setRegionId(resultSet.getInt("region_id"));
                kingRequest.setRequestDate(resultSet.getTimestamp("request_date"));
                kingRequest.setRequestContent(resultSet.getString("request_content"));
                kingRequest.setDeadline(resultSet.getDate("deadline"));
                kingRequest.setDone(resultSet.getBoolean("isDone"));

                kingRequests.add(kingRequest);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e);;
        }

        return kingRequests;
    }
    
    public KingRequest getById(int id) {
        KingRequest result = null;
        String sql = "SELECT * FROM King_Request where id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                KingRequest kingRequest = new KingRequest();
                kingRequest.setId(resultSet.getInt("id"));
                kingRequest.setRegionId(resultSet.getInt("region_id"));
                kingRequest.setRequestDate(resultSet.getTimestamp("request_date"));
                kingRequest.setRequestContent(resultSet.getString("request_content"));
                kingRequest.setDeadline(resultSet.getDate("deadline"));
                kingRequest.setDone(resultSet.getBoolean("isDone"));
                result = kingRequest;
                
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return result;
    }
    
    public List<KingRequest> getByRegionId(int id) {
        List<KingRequest> kingRequests = new ArrayList<>();
        String sql = "SELECT * FROM King_Request where region_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                KingRequest kingRequest = new KingRequest();
                kingRequest.setId(resultSet.getInt("id"));
                kingRequest.setRegionId(resultSet.getInt("region_id"));
                kingRequest.setRequestDate(resultSet.getTimestamp("request_date"));
                kingRequest.setRequestContent(resultSet.getString("request_content"));
                kingRequest.setDeadline(resultSet.getDate("deadline"));
                kingRequest.setDone(resultSet.getBoolean("isDone"));

                kingRequests.add(kingRequest);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e);;
        }

        return kingRequests;
    }
    
    public List<KingRequest> getListByPage(List<KingRequest> list,
            int start, int end) {
        List<KingRequest> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
    
    public void insert(KingRequest newKingRequest) {
        String sql = "insert into King_Request(region_id, request_date, request_content, deadline) values(?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // set cac tham so vao query de insert
            st.setInt(1, newKingRequest.getRegionId());
            st.setTimestamp(2, newKingRequest.getRequestDate());
            st.setString(3, newKingRequest.getRequestContent());
            st.setDate(4, newKingRequest.getDeadline());
            st.executeUpdate();
            System.out.println("Inserted!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args) {
        KingRequestDao kd = new KingRequestDao();
        
        Timestamp date1 = Timestamp.valueOf(LocalDateTime.now());
        Date date2 = Date.valueOf(LocalDate.now().plusDays(7));
        KingRequest k = new KingRequest(1, date1, "Report!!", date2);
//        kd.insert(k);
        List<KingRequest> list = kd.getAll();
        for (KingRequest i : list) {
            System.out.println(i.toString());
        }
        KingRequest k2 = kd.getById(1);
        System.out.println(k2);
    }
}
