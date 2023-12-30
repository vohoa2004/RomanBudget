/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TaxCategoryDao;
import dao.TaxDao;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Region;
import model.Tax;
import model.TaxCategory;

/**
 *
 * @author vothimaihoa
 */
public class TaxServlet extends HttpServlet {

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
        TaxDao td = new TaxDao();        
        HttpSession session = request.getSession();
        List<Tax> list1 = getListByRole(session, td);
        //phan trang
        int page; // page la bien dung de lay ra page number cua trang hien tai (kieu int)
        String xpage = request.getParameter("page"); // xac dinh request duoc gui tu trang nao
        if (xpage == null) {
            page = 1; // lan dau tien chay khong co trang => cho chay ra trang 1
        } else {
            page = Integer.parseInt(xpage);
        }
        
        List<Tax> list = paging(page, 5, list1, td);
        request.setAttribute("data", list);
        request.setAttribute("page", page); // danh dau trang hien tai duoc chon
        request.setAttribute("num", numOfPage(list1.size(), 5));
        request.setAttribute("currentPage", "tax");

        request.getRequestDispatcher("tax.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account acc = (Account)session.getAttribute("account");

        if (acc.getUsertype().equals("emperor")) {
            doPostEmperor(request, response);
        }
        else{
            doPostConsul(request, response);
        }
        response.sendRedirect("tax");
    }
    
    private void doPostEmperor (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String region_id_raw = request.getParameter("regionId");
        String taxAmount_raw = request.getParameter("taxAmount");
        String tributeAmount_raw = request.getParameter("tributeAmount");
        String deadline_raw = request.getParameter("deadline");
        String tax_category_raw = request.getParameter("taxCategory");
        String year_raw = request.getParameter("year");
        int regionId, taxCategory, year;
        double taxAmount, tributeAmount;
        Date deadline;
        try {
            // chuyen vao dto
            regionId = Integer.parseInt(region_id_raw);
            taxAmount = Double.parseDouble(taxAmount_raw);
            tributeAmount = Double.parseDouble(tributeAmount_raw);
            deadline = Date.valueOf(deadline_raw);
            taxCategory = Integer.parseInt(tax_category_raw);
            
            TaxCategoryDao tcd = new TaxCategoryDao();
            TaxCategory tc = tcd.getById(taxCategory);
            year = Integer.parseInt(year_raw);
            Tax newTax = new Tax(regionId, taxAmount, tributeAmount, deadline,year, tc);
            
            TaxDao td = new TaxDao();
            td.insert(newTax);
        } catch (NumberFormatException e) {
            System.out.println("Cannot cast!");
        }
    }
    
    private void doPostConsul (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tax_id_raw = request.getParameter("taxId");
        int taxId = Integer.parseInt(tax_id_raw);
        Date paidDate = Date.valueOf(LocalDate.now());
        TaxDao td = new TaxDao();
        td.update(paidDate, taxId);
    }
    
    private List<Tax> getListByRole(HttpSession session, TaxDao td) {
        List<Tax> list;
        Account acc = (Account)session.getAttribute("account");

        if (acc.getUsertype().equals("emperor")){
        list = td.getAll();}
        else {
            Region region = (Region) session.getAttribute("region");
            list = td.getByRegionId(region.getId());
        }
        return list;
    }

    private List<Tax> paging(int page, int numperpage,
            List<Tax> originalList, TaxDao fd) {
        List<Tax> result;
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
