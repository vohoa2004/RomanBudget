/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author vothimaihoa
 */
public class LogoutServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a != null) {
            session.removeAttribute("account"); // remove account when logout
        }
        response.sendRedirect("index.html");
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
        String currentPass = request.getParameter("currentPassword");

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        AccountDao acd = new AccountDao();
        String message;
        if (!acc.getPassword().equals(currentPass)) {
            message = "Wrong current password!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("update-pass.jsp").forward(request, response);
        } else {
            String newpass = request.getParameter("newpass");
            String confirm = request.getParameter("confirm");

            if (!confirm.equals(newpass)) {
                message = "Confirm password must match new password!";
                request.setAttribute("message", message);
                request.getRequestDispatcher("update-pass.jsp").forward(request, response);
            } else {

                acd.changePassword(newpass, acc.getId());
                message = "Update password successfully! Please login again!";
                request.setAttribute("message", message);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }

}
