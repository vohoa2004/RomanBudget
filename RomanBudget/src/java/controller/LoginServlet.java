/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDao;
import dao.ConsulDao;
import dao.RegionDao;
import dao.TaxCategoryDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Consul;
import model.Region;
import model.TaxCategory;

/**
 *
 * @author vothimaihoa
 */
public class LoginServlet extends HttpServlet {

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
        response.sendRedirect("login.jsp");
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
        AccountDao acd = new AccountDao();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String usertype = request.getParameter("usertype");
        Account account = acd.login(username, password, usertype);

        if (account != null) {// neu ton tai account <=> login thanh cong => tao session cho account de luu phien hoat dong
            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            if (account.getUsertype().equals("consul")) {
                RegionDao rd = new RegionDao();
                try {
                    Region r = rd.getRegionByConsulId(account.getId());
                    session.setAttribute("region", r);

                } catch (Exception e) {
                    response.sendRedirect("index.html");
                }

                ConsulDao cd = new ConsulDao();
                Consul c = cd.getById(account.getId());
                session.setAttribute("consul", c);
            } else {
                RegionDao rd = new RegionDao();
                TaxCategoryDao tcd = new TaxCategoryDao();
                List<Region> r = rd.getAll();
                List<TaxCategory> tc = tcd.getAll();
                session.setAttribute("regions", r);
                session.setAttribute("taxCategories", tc);
            }

            response.sendRedirect("region");
        } else {
            String error = "Invalid account!";
            request.setAttribute("error", error);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

}
