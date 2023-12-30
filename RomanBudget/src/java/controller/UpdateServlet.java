/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ReportDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Report;

/**
 *
 * @author vothimaihoa
 */
public class UpdateServlet extends HttpServlet {
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        response.setContentType("text/html;charset=UTF-8");
        String status = request.getParameter("selectedStatus");
        int report_id = Integer.parseInt(request.getParameter("reportId1"));

        ReportDao rd = new ReportDao();
        Report report = rd.getById(report_id);
        report.setStatus(status);
        rd.update(report);
        if (status.equals("rejected")) {
            request.setAttribute("kingRequest", report.getRequest());
            request.getRequestDispatcher("king-request").forward(request, response);
        }
        response.sendRedirect("report");
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
        response.setContentType("text/html;charset=UTF-8");
        String comment = request.getParameter("comment");
        int report_id = Integer.parseInt(request.getParameter("reportId2"));

        ReportDao rd = new ReportDao();
        Report report = rd.getById(report_id);
        report.setComment(comment);
        rd.update(report);
        response.sendRedirect("report");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
