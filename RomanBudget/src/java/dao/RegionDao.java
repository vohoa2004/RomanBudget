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
                + "on r.id = rc.region_id "
                + "where rc.status = 1";
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

    public List<Region> getListByPage(List<Region> list,
            int start, int end) {
        List<Region> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public Region getRegionById(int id) {
        Region result = null;
        ConsulDao cd = new ConsulDao();
        String sql = "select * "
                + "from Region r join Region_Consul rc "
                + "on r.id = rc.region_id "
                + "where rc.status = 1 and r.id = ?";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
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

    public static void main(String[] args) {
        RegionDao rd = new RegionDao();
        List<Region> list = rd.getAll();
        for (Region r : list) {
            System.out.println(r.toString());
            System.out.println(rd.getBudgetOfRegion(r.getId()));
        }
    }
}
