/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RegionDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Region;

/**
 *
 * @author vothimaihoa
 */
public class RegionServlet extends HttpServlet {

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

        RegionDao rd = new RegionDao();
        HttpSession session = request.getSession();
        List<Region> list1 = getListByRole(session, rd, response);
        // get budget
        for (Region r : list1) {
            double budget = rd.getBudgetOfRegion(r.getId());
            r.setTotalBudget(budget);
        }

        int page;
        String xpage = request.getParameter("page"); // xac dinh request duoc gui tu trang nao
        if (xpage == null) {
            page = 1; // lan dau tien chay khong co trang => cho chay ra trang 1
        } else {
            page = Integer.parseInt(xpage);
        }

        List<Region> list = paging(page, 5, list1, rd);
        request.setAttribute("data", list);
        request.setAttribute("page", page); // danh dau trang hien tai duoc chon
        request.setAttribute("num", numOfPage(list1.size(), 5));
        request.setAttribute("currentPage", "region");

        request.getRequestDispatcher("region.jsp").forward(request, response);
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
        RegionDao rd = new RegionDao();

        // filter time
        String year1_raw = request.getParameter("year1");
        String year2_raw = request.getParameter("year2");
        String month1_raw = request.getParameter("month1");
        String month2_raw = request.getParameter("month2");

        // lay ds region
        HttpSession session = request.getSession();
        List<Region> list1 = getListByRole(session, rd, response);
        if (year1_raw.length() <= 0 || year2_raw.length() <= 0 || month1_raw.length() <= 0 || month2_raw.length() <= 0) {
            request.setAttribute("annouce", "Please enter full 4 parameters to filter!");
            for (Region r : list1) {
                r.setTotalBudget(rd.getBudgetOfRegion(r.getId()));
            }
        } else {
            int year1, year2, month1, month2;
            year1 = Integer.parseInt(year1_raw);
            year2 = Integer.parseInt(year2_raw);
            month1 = Integer.parseInt(month1_raw);
            month2 = Integer.parseInt(month2_raw);
            for (Region r : list1) {
                r.setTotalBudget(rd.getBudgetOfRegion(r.getId(), year1, year2, month1, month2));
            }
        }

        // phan trang
        int page;
        String xpage = request.getParameter("page"); // xac dinh request duoc gui tu trang nao

        if (xpage == null) {
            page = 1; // lan dau tien chay khong co trang => cho chay ra trang 1
        } else {
            page = Integer.parseInt(xpage);
        }

        List<Region> list = paging(page, 5, list1, rd);
        // set attribute
        request.setAttribute("data", list);
        request.setAttribute("page", page); // danh dau trang hien tai duoc chon
        request.setAttribute("num", numOfPage(list1.size(), 5));
        request.setAttribute("currentPage", "region");

        request.getRequestDispatcher("region.jsp").forward(request, response);
    }

    private List<Region> getListByRole(HttpSession session, RegionDao rd, HttpServletResponse response)
            throws ServletException, IOException {
        List<Region> list = new ArrayList<>();
        Account acc = (Account) session.getAttribute("account");

        if (acc.getUsertype().equals("emperor")) {
            list = rd.getAll();
        } else {
            try {
                Region region = (Region) session.getAttribute("region");
                list.add(rd.getRegionById(region.getId()));
            } catch (Exception e) {
//                response.sendError(404, "You have no regions to manage!");
                response.sendRedirect("index.html");
            }
        }
        return list;
    }

    private List<Region> paging(int page, int numperpage, List<Region> originalList, RegionDao rd) {
        List<Region> result;
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
