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
import model.Region;
import model.Versaille;
import utils.DBContext;

/**
 *
 * @author vothimaihoa
 */
public class VersailleDao extends DBContext{
    public List<Versaille> getAll() {
        List<Versaille> list = new ArrayList<>();
        RegionDao rd = new RegionDao();
        String sql = "select v.*, r.name as regionName from Versaille "
                + "v inner join Region r on v.region_id = r.id";
        try {
           
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Versaille v = new Versaille();
                v.setId(rs.getInt("id"));
                v.setName(rs.getString("name"));
                v.setImage(rs.getString("image"));
                Region r = new Region();
                r.setId(rs.getInt("region_id"));
                r.setName(rs.getString("regionName"));
                v.setRegion(r);
              list.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Versaille> getByRegionId(int regionId) {
        List<Versaille> list = new ArrayList<>();
        RegionDao rd = new RegionDao();
        String sql = "select v.*, r.name as regionName from Versaille v inner join"
                + " Region r on v.region_id = r.id where region_id = ?";
        try {
           
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, regionId);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Versaille v = new Versaille();
                v.setId(rs.getInt("id"));
                v.setName(rs.getString("name"));
                v.setImage(rs.getString("image"));
                 Region r = new Region();
                r.setId(rs.getInt("region_id"));
                r.setName(rs.getString("regionName"));
                v.setRegion(r);
                list.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Versaille> getListByPage(List<Versaille> list,
            int start, int end) {
        List<Versaille> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
    
    public static void main(String[] args) {
        VersailleDao vd = new VersailleDao();
        List<Versaille> v = vd.getAll();
        for (Versaille x : v) {
            System.out.println(x.toString());
            System.out.println(v.size());
        }
    }
    
}
