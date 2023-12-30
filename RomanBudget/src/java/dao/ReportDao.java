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
import model.KingRequest;
import model.Report;
import utils.DBContext;

/**
 *
 * @author vothimaihoa
 */
public class ReportDao extends DBContext {

    public List<Report> getAll() {
        List<Report> reports = new ArrayList<>();
        String query = "select k.[request_content], r.* \n"
                + "from [dbo].[Report] r join [dbo].[King_Request] k on r.request_id = k.id";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Report report = new Report();
                report.setReportId(resultSet.getInt("report_id"));

                KingRequestDao kd = new KingRequestDao();
                KingRequest k = kd.getById(resultSet.getInt("request_id"));
                report.setRequest(k);
                report.setWrittenDate(resultSet.getDate("written_date"));
                report.setDeliveredDate(resultSet.getTimestamp("delivered_date"));
                report.setLocation(resultSet.getString("location"));
                report.setReportData(resultSet.getString("report_data"));
                report.setTitle(resultSet.getString("report_title"));
                report.setLink(resultSet.getBoolean("report_type"));
                report.setStatus(resultSet.getString("status"));
                report.setComment(resultSet.getString("comment"));

                ConsulDao cd = new ConsulDao();
                Consul c = cd.getById(resultSet.getInt("writer_id"));
                report.setConsul(c);

                reports.add(report);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return reports;
    }

    public Report getById(int id) {
        Report result = null;
        String query = "select k.[request_content], r.* \n"
                + "from [dbo].[Report] r join [dbo].[King_Request] k on r.request_id = k.id "
                + "where report_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Report report = new Report();
                report.setReportId(resultSet.getInt("report_id"));
                KingRequestDao kd = new KingRequestDao();
                KingRequest k = kd.getById(resultSet.getInt("request_id"));
//                k.setId(resultSet.getInt("request_id"));
//                k.setRequestContent(resultSet.getString("request_content"));
                report.setRequest(k);
                report.setWrittenDate(resultSet.getDate("written_date"));
                report.setDeliveredDate(resultSet.getTimestamp("delivered_date"));
                report.setLocation(resultSet.getString("location"));
                report.setReportData(resultSet.getString("report_data"));
                report.setTitle(resultSet.getString("report_title"));
                report.setLink(resultSet.getBoolean("report_type"));
                report.setStatus(resultSet.getString("status"));
                report.setComment(resultSet.getString("comment"));

                ConsulDao cd = new ConsulDao();
                Consul c = cd.getById(resultSet.getInt("writer_id"));
                report.setConsul(c);
                result = report;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return result;
    }

    public List<Report> getByRegionId(int regionId) {
        List<Report> reports = new ArrayList<>();
        String query = "select k.[request_content], rp.* \n"
                + "                        from [dbo].[Report] rp join [dbo].[King_Request] k on rp.request_id = k.id\n"
                + "     where rp.request_id in (select [id]\n"
                + "				from [dbo].[King_Request] k\n"
                + "				where [region_id] = ?\n"
                + "				)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, regionId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Report report = new Report();
                report.setReportId(resultSet.getInt("report_id"));

                KingRequest k = new KingRequest();
                k.setId(resultSet.getInt("request_id"));
                k.setRequestContent(resultSet.getString("request_content"));
                report.setRequest(k);
                report.setWrittenDate(resultSet.getDate("written_date"));
                report.setDeliveredDate(resultSet.getTimestamp("delivered_date"));
                report.setLocation(resultSet.getString("location"));
                report.setReportData(resultSet.getString("report_data"));
                report.setTitle(resultSet.getString("report_title"));
                report.setLink(resultSet.getBoolean("report_type"));
                report.setStatus(resultSet.getString("status"));
                report.setComment(resultSet.getString("comment"));

                reports.add(report);
            }
        } catch (SQLException e) {
            System.out.println(e);;
        }

        return reports;
    }

    public void insert(Report newReport) {
        String sql = "insert into Report(request_id, written_date, delivered_date, location, "
                + "report_data, report_title, report_type, writer_id) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // Set các tham số vào query để insert
            st.setInt(1, newReport.getRequest().getId());
            st.setDate(2, newReport.getWrittenDate());
            st.setTimestamp(3, newReport.getDeliveredDate());
            st.setString(4, newReport.getLocation());
            st.setString(5, newReport.getReportData());
            st.setString(6, newReport.getTitle());
            st.setBoolean(7, newReport.getLink());
            st.setInt(8, newReport.getConsul().getId());

            st.executeUpdate();
            System.out.println("Inserted!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void update(Report report) {
        String sql = "update Report set status = ?, comment = ? where report_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // Set các tham số vào query để update
            st.setString(1, report.getStatus());
            st.setString(2, report.getComment());
            st.setInt(3, report.getReportId());

            st.executeUpdate();
            System.out.println("Updated!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<Report> getListByPage(List<Report> list,
            int start, int end) {
        List<Report> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public static void main(String[] args) {
        ReportDao rd = new ReportDao();
//        Date date1 = Date.valueOf(LocalDate.now());
//        Date date2 = Date.valueOf(LocalDate.now().plusDays(7));
//        rd.insert(new Report(4, date1, date2, "Viet Nam", "https://translate.google.com/", Boolean.TRUE, "Bao cao demo"));
        List<Report> list = rd.getAll();
        for (Report r : list) {
            System.out.println(r.toString());
        }
        System.out.println(rd.getById(1));
//        Report report = rd.getById(3);
//        System.out.println(report);
//        report.setStatus("approved");
//        rd.update(report);
//        System.out.println(report.toString());
    }
}
