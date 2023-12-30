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
import model.Versaille;
import model.VersailleSituation;
import utils.DBContext;

/**
 *
 * @author vothimaihoa
 */
public class VersailleSituationDao extends DBContext {

    public List<VersailleSituation> getAll() {
        List<VersailleSituation> list = new ArrayList<>();
        String sql = "select v.name, vs.* \n"
                + "from [dbo].[Versaille_Situation] vs inner join [dbo].[Versaille] v \n"
                + "on vs.versaille_id = v.id ";
        try {

            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                VersailleSituation vs = new VersailleSituation();
                Versaille v = new Versaille();
                v.setId(rs.getInt("versaille_id"));
                v.setName(rs.getString("name"));
                vs.setVersaille(v);
                vs.setFoodSupply(rs.getInt("food_supply"));
                vs.setGrowthRate(rs.getDouble("growth_rate"));
                vs.setRevoltRate(rs.getDouble("revolt_rate"));
                vs.setMarketValue(rs.getDouble("market_value"));
                vs.setQuarter(rs.getInt("quarter"));
                vs.setYear(rs.getInt("year"));
                list.add(vs);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<VersailleSituation> getByVersailleId(int versailleId) {
        List<VersailleSituation> list = new ArrayList<>();
        String sql = "select v.name, vs.* \n"
                + "from [dbo].[Versaille_Situation] vs inner join [dbo].[Versaille] v \n"
                + "on vs.versaille_id = v.id "
                + "where versaille_id = ?";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, versailleId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                VersailleSituation vs = new VersailleSituation();
                Versaille v = new Versaille();
                v.setId(rs.getInt("versaille_id"));
                v.setName(rs.getString("name"));
                vs.setVersaille(v);
                vs.setFoodSupply(rs.getInt("food_supply"));
                vs.setGrowthRate(rs.getDouble("growth_rate"));
                vs.setRevoltRate(rs.getDouble("revolt_rate"));
                vs.setMarketValue(rs.getDouble("market_value"));
                vs.setQuarter(rs.getInt("quarter"));
                vs.setYear(rs.getInt("year"));
                list.add(vs);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<VersailleSituation> getListByPage(List<VersailleSituation> list,
            int start, int end) {
        List<VersailleSituation> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public static void main(String[] args) {
        VersailleSituationDao vsd = new VersailleSituationDao();
        List<VersailleSituation> list = vsd.getAll();
        for (VersailleSituation v : list) {
            System.out.println(v.toString());
        }
    }
}
