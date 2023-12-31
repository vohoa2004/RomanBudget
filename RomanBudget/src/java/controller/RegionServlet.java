/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RegionDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Region;
import model.Term;

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
        List<Term> list1 = getListByRole(session, rd, response);
        // get budget
        for (Term r : list1) {
            double budget = rd.getBudgetOfRegion(r.getRegion().getId());
            r.getRegion().setTotalBudget(budget);
        }

        int page;
        String xpage = request.getParameter("page"); // xac dinh request duoc gui tu trang nao
        if (xpage == null) {
            page = 1; // lan dau tien chay khong co trang => cho chay ra trang 1
        } else {
            page = Integer.parseInt(xpage);
        }

        List<Term> list = paging(page, 5, list1, rd);
        
        double sum = getTotalBudgetOfRoman(list1);
        request.setAttribute("sum", sum);
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
        String action = request.getParameter("action");
        if ("filter".equals(action)) {
            doPostFilter(request, response, rd);
        } else {
            doPostAssign(request, response, rd);
        }
        request.getRequestDispatcher("region.jsp").forward(request, response);
    }

    private void doPostFilter(HttpServletRequest request, HttpServletResponse response, RegionDao rd)
            throws ServletException, IOException {
        
        // lay ds region
        HttpSession session = request.getSession();
        List<Term> list1 = getListByRole(session, rd, response);
        
        // filter time
        String year1_raw = request.getParameter("year1");
        String year2_raw = request.getParameter("year2");
        String month1_raw = request.getParameter("month1");
        String month2_raw = request.getParameter("month2");
        
        // lay list sau khi filter
        list1 = filter(year1_raw, year2_raw, month1_raw, month2_raw, request, response, rd, list1);
        
        // calculate total budget of all regions using map by region id
        double sum = getTotalBudgetOfRoman(list1);
        
        // phan trang
        int page;
        String xpage = request.getParameter("page"); // xac dinh request duoc gui tu trang nao

        if (xpage == null) {
            page = 1; // lan dau tien chay khong co trang => cho chay ra trang 1
        } else {
            page = Integer.parseInt(xpage);
        }

        List<Term> list = paging(page, 5, list1, rd);
        // set attribute
        request.setAttribute("sum", sum);
        request.setAttribute("data", list);
        request.setAttribute("page", page); // danh dau trang hien tai duoc chon
        request.setAttribute("num", numOfPage(list1.size(), 5));
        request.setAttribute("currentPage", "region");
    }

    private List<Term> filter(String year1_raw, String year2_raw, String month1_raw, String month2_raw, 
            HttpServletRequest request, HttpServletResponse response, RegionDao rd, List<Term> list1)
            throws ServletException, IOException {
        List<Term> result;
        if (year1_raw.length() <= 0 || year2_raw.length() <= 0 || month1_raw.length() <= 0 || month2_raw.length() <= 0) {
            request.setAttribute("annouce", "Please enter full 4 parameters to filter!");
            result = list1;
            for (Term t : list1) {
                Region region = t.getRegion();
                int regionId = t.getRegion().getId();
                region.setTotalBudget(rd.getBudgetOfRegion(regionId));
            }
        } else {
            int year1, year2, month1, month2;
            year1 = Integer.parseInt(year1_raw);
            year2 = Integer.parseInt(year2_raw);
            month1 = Integer.parseInt(month1_raw);
            month2 = Integer.parseInt(month2_raw);
            result = getTermsInTime(year1, year2, list1);
            for (Term t : result) {
                Region region = t.getRegion();
                int regionId = t.getRegion().getId();
                double totalBudgetInTime = rd.getBudgetOfRegion(regionId, year1, year2, month1, month2);
                region.setTotalBudget(totalBudgetInTime);
            }
        }
        return result;
    }

    private void doPostAssign(HttpServletRequest request, HttpServletResponse response, RegionDao rd)
            throws ServletException, IOException {
        int consulId, regionId, startYear;
        try {
            consulId = Integer.parseInt(request.getParameter("consulId"));
            regionId = Integer.parseInt(request.getParameter("regionId"));
            startYear = Integer.parseInt(request.getParameter("startYear"));
            rd.insertToRegionConsul(consulId, regionId, startYear);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private List<Term> getListByRole(HttpSession session, RegionDao rd, HttpServletResponse response)
            throws ServletException, IOException {
        List<Term> list = new ArrayList<>();
        Account acc = (Account) session.getAttribute("account");

        if (acc.getUsertype().equals("emperor")) {
            list = rd.getAllTerms();
        } else {
            try {
                Region region = (Region) session.getAttribute("region");
                list = rd.getRegionById(region.getId());
            } catch (Exception e) {
                response.sendRedirect("index.html");
            }
        }
        return list;
    }
    
    private List<Term> getTermsInTime(int year1, int year2, List<Term> list1) {
        List<Term> result = new ArrayList<>();
        for (Term t : list1) {
            if (!(t.getStartYear() > year2 || t.getStartYear() + 3 < year1)) {
                result.add(t);
            }
        }
        return result;
    }

    private List<Term> paging(int page, int numperpage, List<Term> originalList, RegionDao rd) {
        List<Term> result;
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
    
    private double getTotalBudgetOfRoman(List<Term> list1) {
        double sum = 0;
        Map<Integer, Term> map = new HashMap<>();
        for (Term t : list1) {
            map.put(t.getRegion().getId(), t);
        }
        for (Term t : map.values()) {
            sum += t.getRegion().getTotalBudget();
        }
        return sum;
    }

}
