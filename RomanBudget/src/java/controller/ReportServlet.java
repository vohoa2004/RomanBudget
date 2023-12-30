/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.KingRequestDao;
import dao.ReportDao;
import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Consul;
import model.KingRequest;
import model.Region;
import model.Report;

/**
 *
 * @author vothimaihoa
 */
public class ReportServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // lay data in table
        ReportDao rd = new ReportDao();

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        Region region = (Region) session.getAttribute("region");
        List<Report> list1 = getListByRole(acc, region, rd);
        
        sort(list1);

        // get request ID if request ID is auto filled
        String reqid_raw = request.getParameter("id");
        Integer reqId = null;
        if (reqid_raw != null) {
            reqId = Integer.parseInt(reqid_raw);
        }

        // phan trang
        int page; // page la bien dung de lay ra page number cua trang hien tai (kieu int)
        String xpage = request.getParameter("page"); // xac dinh request duoc gui tu trang nao
        if (xpage == null) {
            page = 1; // lan dau tien chay khong co trang => cho chay ra trang 1
        } else {
            page = Integer.parseInt(xpage);
        }
        List<Report> list = paging(page, 5, list1, rd);

        request.setAttribute("data", list);
        request.setAttribute("page", page); // danh dau trang hien tai duoc chon
        request.setAttribute("num", numOfPage(list1.size(), 5));
        request.setAttribute("currentPage", "report");
        request.setAttribute("req_id", reqId);

        request.getRequestDispatcher("report.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String writtenDate_raw = request.getParameter("writtenDate");
        String requestId_raw = request.getParameter("requestId");
        String location = request.getParameter("location");
        String reportData = request.getParameter("reportData");
        String link = request.getParameter("link");
        String title = request.getParameter("title");

        HttpSession session = request.getSession();
        Consul consul = (Consul) session.getAttribute("consul");

        // Chuyển đổi dữ liệu
        int requestId;
        Date writtenDate;
        try {
            // Chuyển đổi từ string sang java.sql.Date
            writtenDate = Date.valueOf(writtenDate_raw);

            requestId = Integer.parseInt(requestId_raw);
            // Xử lý kiểu báo cáo
            boolean reportType = link.equals("link");

            // Tạo Report mới
            Report newReport = new Report();
            KingRequestDao kd = new KingRequestDao();
            KingRequest k = kd.getById(requestId);
            newReport.setConsul(consul);
            newReport.setRequest(k);
            newReport.setWrittenDate(writtenDate);
            newReport.setDeliveredDate(new java.sql.Timestamp(System.currentTimeMillis())); // Ngày gửi report là thời gian hiện tại
            newReport.setLocation(location);
            newReport.setTitle(title);
            newReport.setReportData(reportData);
            newReport.setLink(reportType);

            // Gọi phương thức insert từ ReportDao để thêm vào cơ sở dữ liệu
            ReportDao reportDao = new ReportDao();
            reportDao.insert(newReport);
            System.out.println(newReport.toString());

            // Chuyển hướng sau khi thêm báo cáo thành công
            response.sendRedirect("report");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage()); // Xử lý lỗi khi chuyển đổi dữ liệu
        }
    }

    private List<Report> getListByRole(Account acc, Region region, ReportDao rd) {
        List<Report> list;
        if (acc.getUsertype().equals("emperor")) {
            list = rd.getAll();
        } else {
            list = rd.getByRegionId(region.getId());
        }
        return list;
    }

    private void sort(List<Report> list) {
        //sort by delivered date
        Comparator<Report> com = (Report f1, Report f2) -> {
            int result;
            if (f1.getDeliveredDate().after(f2.getDeliveredDate())) {
                result = -1;
            } else if (f2.getDeliveredDate().after(f1.getDeliveredDate())) {
                result = 1;
            } else {
                result = 0;
            }
            return result;
        };
        Collections.sort(list, com);
    }

    private List<Report> paging(int page, int numperpage,
            List<Report> originalList, ReportDao fd) {
        List<Report> result;
        int size = originalList.size();
        int start, end;
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);
        result = fd.getListByPage(originalList, start, end);
        return result;
    }

    private int numOfPage(int size, int numperpage) {
        return (size % numperpage == 0) ? (size / numperpage) : (size / numperpage + 1);
    }

}
