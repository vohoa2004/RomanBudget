/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.KingRequestDao;
import java.io.IOException;
import java.sql.Date;
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
import model.KingRequest;
import model.Region;

/**
 *
 * @author vothimaihoa
 */
public class KingRequestServlet extends HttpServlet {

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
        KingRequestDao kd = new KingRequestDao();
        HttpSession session = request.getSession();
        Account acc = (Account)session.getAttribute("account");
        Region region = (Region) session.getAttribute("region");
        List<KingRequest> list1 = getListByRole(acc, region, kd);
        
        sort(list1);
        int page; // page la bien dung de lay ra page number cua trang hien tai (kieu int)       
        String xpage = request.getParameter("page"); // xac dinh request duoc gui tu trang nao
        if (xpage == null) {
            page = 1; // lan dau tien chay khong co trang => cho chay ra trang 1
        } else {
            page = Integer.parseInt(xpage);
        }
        
        List<KingRequest> list = paging(page, 5, list1, kd);
        request.setAttribute("data", list);
        request.setAttribute("page", page); // danh dau trang hien tai duoc chon
        request.setAttribute("num", numOfPage(list1.size(), 5));
        request.setAttribute("currentPage", "king-request");

        request.getRequestDispatcher("king-request.jsp").forward(request, response);
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
        String regionId_raw = request.getParameter("regionId");
        String requestContent = request.getParameter("requestContent");
        String deadline_raw = request.getParameter("deadline");
        Timestamp requestDate = Timestamp.valueOf(LocalDateTime.now());

        Date deadline;
        int regionId;
        try {
            deadline = Date.valueOf(deadline_raw);
            regionId = Integer.parseInt(regionId_raw);

            KingRequestDao kingRequestDao = new KingRequestDao();
            KingRequest newKingRequest = new KingRequest(regionId, requestDate, requestContent, deadline);

            // Call the DAO method to insert the new King Request
            kingRequestDao.insert(newKingRequest);

            response.sendRedirect("king-request"); // Redirect to a success page
        } catch (NumberFormatException e) {
            // Handle exceptions
        }
    }
    
    private List<KingRequest> getListByRole(Account acc, Region region, KingRequestDao kd) {
        List<KingRequest> list;
        if (acc.getUsertype().equals("emperor")) {
            list = kd.getAll();
        }
        else {
            list = kd.getByRegionId(region.getId());
        }
        return list;
    }

    private void sort(List<KingRequest> list) {
        Comparator<KingRequest> com = (KingRequest f1, KingRequest f2) -> {
            int result;
            if (f1.getRequestDate().after(f2.getRequestDate())) {
                result = -1;
            }
            else if (f2.getRequestDate().after(f1.getRequestDate())) {
                result = 1;
            }
            else result = 0;
            return result;
        };
        Collections.sort(list, com);
    }
    
    private List<KingRequest> paging(int page, int numperpage,
            List<KingRequest> originalList, KingRequestDao fd) {
        List<KingRequest> result;
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
