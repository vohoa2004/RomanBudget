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
import model.Consul;
import model.Region;
import model.Term;
import utils.DBContext;

/**
 *
 * @author vothimaihoa
 */
public class RegionDao extends DBContext {

    public List<Region> getAll() {

        List<Region> list = new ArrayList<>();
        ConsulDao cd = new ConsulDao();
        String sql = "select * "
                + "from Region r join Region_Consul rc "
                + "on r.id = rc.region_id";
        try {

            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Region r = new Region();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                Consul c = cd.getById(rs.getInt("consul_id"));
                r.setConsul(c);
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    

    public Double getBudgetOfRegion(int region_id) {
        Double result = null;
        String sql = "select dbo.calBudget(?) as result";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, region_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getDouble("result");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public Double getBudgetOfRegion(int region_id, int year1, int year2, int month1, int month2) {
        Double result = null;
        String sql = "select dbo.calBudget2(?,?,?,?,?) as result";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, region_id);
            st.setInt(2, year1);
            st.setInt(3, year2);
            st.setInt(4, month1);
            st.setInt(5, month2);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getDouble("result");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public List<Term> getListByPage(List<Term> list,
            int start, int end) {
        List<Term> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Term> getRegionById(int id) {
        List<Term> result = new ArrayList<>();
        ConsulDao cd = new ConsulDao();
        String sql = "select * "
                + "from Region r join Region_Consul rc "
                + "on r.id = rc.region_id "
                + "where rc.status = 1 and r.id = ?";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Region r = new Region();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                Consul c = cd.getById(rs.getInt("consul_id"));
                Term t = new Term();
                t.setStartYear(rs.getInt("start_year"));
                t.setStatus(rs.getBoolean("status"));
                t.setConsul(c);
                t.setRegion(r);
                result.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public Region getRegionByConsulId(int cid) {
        Region result = null;
        ConsulDao cd = new ConsulDao();
        String sql = "select * "
                + "from Region r join Region_Consul rc "
                + "on r.id = rc.region_id "
                + "where rc.status = 1 and rc.consul_id = ?";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Region r = new Region();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                Consul c = cd.getById(rs.getInt("consul_id"));
                r.setConsul(c);
                result = r;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public List<Term> getAllTerms() {

        List<Term> list = new ArrayList<>();
        ConsulDao cd = new ConsulDao();
        String sql = "select * "
                + "from Region r join Region_Consul rc "
                + "on r.id = rc.region_id";
        try {

            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Region r = new Region();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                Consul c = cd.getById(rs.getInt("consul_id"));
                Term t = new Term();
                t.setStartYear(rs.getInt("start_year"));
                t.setStatus(rs.getBoolean("status"));
                t.setConsul(c);
                t.setRegion(r);
                list.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
//    public List<Term> getTermsInTime(int year1, int year2) {
//
//        List<Term> list = new ArrayList<>();
//        ConsulDao cd = new ConsulDao();
//        String sql = "select * "
//                + "from Region r join Region_Consul rc "
//                + "on r.id = rc.region_id "
//                + "except "
//                + "select * "
//                + "from Region r join Region_Consul rc "
//                + "on r.id = rc.region_id "
//                + "where end_year < ? or start_year > ?";
//        try {
//
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, year1);
//            st.setInt(2, year2);
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                Region r = new Region();
//                r.setId(rs.getInt("id"));
//                r.setName(rs.getString("name"));
//                Consul c = cd.getById(rs.getInt("consul_id"));
//                Term t = new Term();
//                t.setStartYear(rs.getInt("start_year"));
//                t.setStatus(rs.getBoolean("status"));
//                t.setConsul(c);
//                t.setRegion(r);
//                list.add(t);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return list;
//    }
    
     public void insertToRegionConsul(int consulId, int regionId, int startYear) {
        String sql = "insert into Region_Consul(consul_id, region_id, start_year, status) values(?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // Set các tham số vào query để insert
            st.setInt(1, consulId);
            st.setInt(2, regionId);
            st.setInt(3, startYear);
            st.setBoolean(4, true);
            st.executeUpdate();
            System.out.println("Inserted!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        RegionDao rd = new RegionDao();
        List<Region> list = rd.getAll();
        for (Region r : list) {
            System.out.println(r.toString());
            System.out.println(rd.getBudgetOfRegion(r.getId()));
        }
        
//        List<Term> list2 = rd.getTermsInTime(2022, 2023);
//        for (Term r : list2) {
//            System.out.println(r.toString());
//        }
    }
}
