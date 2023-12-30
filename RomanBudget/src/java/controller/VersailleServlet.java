/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.VersailleDao;
import dao.VersailleSituationDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Region;
import model.Versaille;
import model.VersailleSituation;

/**
 *
 * @author vothimaihoa
 */
public class VersailleServlet extends HttpServlet {

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
        VersailleDao vd = new VersailleDao();     
        VersailleSituationDao vsd = new VersailleSituationDao();
              
        HttpSession session = request.getSession();
        Account acc = (Account)session.getAttribute("account");
        Region region = (Region) session.getAttribute("region");
        List<Versaille> list1 = getListByRole(vd, acc, region);
        List<VersailleSituation> list2 = vsd.getAll(); // get all
        
        
        int page; // page la bien dung de lay ra page number cua trang hien tai (kieu int)
        String xpage = request.getParameter("page"); // xac dinh request duoc gui tu trang nao
        if (xpage == null) {
            page = 1; // lan dau tien chay khong co trang => cho chay ra trang 1
        } else {
            page = Integer.parseInt(xpage);
        }
        
        List<VersailleSituation> listb = paging(page, 5, list2, vsd); // phan trang VS
        request.setAttribute("data1", list1);
        request.setAttribute("data2", listb);
        request.setAttribute("page", page); // danh dau trang hien tai duoc chon
        request.setAttribute("num", numOfPage(list1.size(), 5));
        request.setAttribute("currentPage", "versaille");

        request.getRequestDispatcher("versaille.jsp").forward(request, response);
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
    
    private List<Versaille> getListByRole(VersailleDao fd,
            Account acc, Region region) {
        List<Versaille> list;
        if (acc.getUsertype().equals("emperor")) {
            list = fd.getAll();
        } else {
            list = fd.getByRegionId(region.getId());
        }
        return list;
    }

    private List<VersailleSituation> paging(int page, int numperpage,
            List<VersailleSituation> originalList, VersailleSituationDao fd) {
        List<VersailleSituation> result;
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
