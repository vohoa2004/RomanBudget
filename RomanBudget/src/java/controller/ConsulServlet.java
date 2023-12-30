/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ConsulDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Consul;

/**
 *
 * @author vothimaihoa
 */
@WebServlet(name = "ConsulServlet", urlPatterns = {"/consul"})
public class ConsulServlet extends HttpServlet {

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
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            doGetList(request, response);
        } else {
            doGetInfo(request, response);
        }
    }

    protected void doGetList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Consul> list1 = new ArrayList<>();
        ConsulDao cd = new ConsulDao();

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc.getUsertype().equals("emperor")) {
            list1 = cd.getAll();
        }

        // paging
        int page;

        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1; // lan dau tien chay khong co trang => cho chay ra trang 1
        } else {
            page = Integer.parseInt(xpage);
        }

        List<Consul> list = paging(page, 5, list1, cd);
        request.setAttribute("data", list);
        request.setAttribute("page", page); // danh dau trang hien tai duoc chon
        request.setAttribute("num", numOfPage(list1.size(), 5));
        request.setAttribute("currentPage", "consul");

        request.getRequestDispatcher("consul.jsp").forward(request, response);
    }

    protected void doGetInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ConsulDao cd = new ConsulDao();
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        Consul consul ;
        if (acc.getUsertype().equals("consul")) {
            consul = (Consul) session.getAttribute("consul");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            consul = cd.getById(id);
        }

        request.setAttribute("consul", consul);
        request.getRequestDispatcher("consul-info.jsp").forward(request, response);
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
    }

    private List<Consul> paging(int page, int numperpage, List<Consul> originalList, ConsulDao rd) {
        List<Consul> result;
        int size = originalList.size();
        int start, end;
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);
        result = rd.getListByPage(originalList, start, end);
        return result;
    }

    private int numOfPage(int size, int numperpage) {
        return (size % numperpage == 0) ? (size / numperpage) : (size / numperpage + 1);
    }

}
