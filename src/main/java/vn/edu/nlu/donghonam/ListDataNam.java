package vn.edu.nlu.donghonam;

import vn.edu.nlu.bean.Product;
import vn.edu.nlu.donghonam.ProductEntityNam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(urlPatterns = "/donghonam")
public class ListDataNam extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<Product> values = new ProductEntityNam().getAll();
      //  ProductEntity pe=new ProductEntity();
      //  Collection<Product> values = pe.getAll();
        request.setAttribute("data", values);
        request.getRequestDispatcher("donghonam.jsp").forward(request,response);

    }
}
