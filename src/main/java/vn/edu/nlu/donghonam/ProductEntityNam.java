package vn.edu.nlu.donghonam;

import vn.edu.nlu.ConnectionDB;
import vn.edu.nlu.bean.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ProductEntityNam {


    public List<Product> getAll() {

        Statement s = null;
        try {
            s = ConnectionDB.connect();
            List<Product> re = new LinkedList<>();
            ResultSet rs = s.executeQuery("select * from product where product.category_id=1");

            while (rs.next()) {
                re.add(new Product(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getLong(4),
                        rs.getLong(5)
                ));

            }
            rs.close();
            s.close();
            return re;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }

    }

    public int insertAll(Collection<Product> map) {


        Statement s = null;
        try {
            s = ConnectionDB.connect();
            String sql = "INSERT INTO product (id,name,image,price,priceSale) VALUES ";
            int i = 0;
            for (Product d : map) {
                if (++i < map.size())
                    sql += "(" + d.getId() + ",\"" + d.getName() + ",\"" + d.getImg() + ",\"" + d.getPrice() + ",\"" + d.getPriceSale() + ")";
                else {
                    sql += "(" + d.getId() + ",\"" + d.getName() + ",\"" + d.getImg() + ",\"" + d.getPrice() + ",\"" + d.getPriceSale() + ")";


                }
            }
           System.out.println(sql);
            //int out;
            //s.executeUpdate(sql);
            s.close();
            return 0;


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return 0;
        }


    }



}