/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FinancialTransactionDao;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.FinancialTransaction;
import model.Region;

/**
 *
 * @author vothimaihoa
 */
public class FinancialTransactionServlet extends HttpServlet {

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

        FinancialTransactionDao fd = new FinancialTransactionDao();
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        Region region = (Region) session.getAttribute("region");
        List<FinancialTransaction> list1 = getListByRole(fd, acc, region);
        sort(list1);

        // get current page
        int page; // page la bien dung de lay ra page number cua trang hien tai (kieu int)        
        String xpage = request.getParameter("page"); // xac dinh request duoc gui tu trang nao
        if (xpage == null) {
            page = 1; // lan dau tien chay khong co trang => cho chay ra trang 1
        } else {
            page = Integer.parseInt(xpage);
        }
        // paging
        List<FinancialTransaction> list = paging(page, 5, list1, fd);

        // set attributes
        request.setAttribute("data", list);
        request.setAttribute("page", page); // danh dau trang hien tai duoc chon
        request.setAttribute("num", numOfPage(list1.size(), 5));
        request.setAttribute("currentPage", "financial-transaction");

        request.getRequestDispatcher("financial.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        String transactionType = request.getParameter("transactionType");
        String amount_raw = request.getParameter("amount");
        String description = request.getParameter("description");
        String regionId_raw = request.getParameter("regionId");
        Timestamp transactionDate = Timestamp.valueOf(LocalDateTime.now());

        Double amount;
        int regionId;
        try {
            regionId = Integer.parseInt(regionId_raw);
            amount = Double.parseDouble(amount_raw);
            FinancialTransactionDao fd = new FinancialTransactionDao();
            FinancialTransaction c1 = new FinancialTransaction(regionId, transactionType, amount, description, transactionDate);
            fd.insert(c1);
            response.sendRedirect("financial-transaction"); // ko truyen gi, muon coi lai danh sach => sendRedirect
        } catch (NumberFormatException e) {
            response.sendRedirect("financial-transaction");
        }
    }

    private List<FinancialTransaction> getListByRole(FinancialTransactionDao fd,
            Account acc, Region region) {
        List<FinancialTransaction> list;
        if (acc.getUsertype().equals("emperor")) {
            list = fd.getAll();
        } else {
            list = fd.getByRegionId(region.getId());
        }
        return list;
    }

    private void sort(List<FinancialTransaction> list1) {
        //sort
        Comparator<FinancialTransaction> com = (FinancialTransaction f1, FinancialTransaction f2) -> {
            int result;
            if (f1.getTransactionDate().after(f2.getTransactionDate())) {
                result = -1;
            } else if (f2.getTransactionDate().after(f1.getTransactionDate())) {
                result = 1;
            } else {
                result = 0;
            }
            return result;
        };
        Collections.sort(list1, com);
    }

    private List<FinancialTransaction> paging(int page, int numperpage,
            List<FinancialTransaction> originalList, FinancialTransactionDao fd) {
        List<FinancialTransaction> result;
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
