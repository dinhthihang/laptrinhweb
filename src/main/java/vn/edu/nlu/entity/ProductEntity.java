package vn.edu.nlu.entity;

import vn.edu.nlu.ConnectionDB;
import vn.edu.nlu.bean.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ProductEntity {


    public List<Product> getAll() {

        Statement s = null;
        try {
            s = ConnectionDB.connect();
            List<Product> re = new LinkedList<>();
            ResultSet rs = s.executeQuery("select * from product where product.category_id=2");

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
    //chuc nang tim kiem
    //tinh so luong  data tim duoc
     public int count(String txtSearch){
         PreparedStatement s=null;
        try{
            String sql="select count(*) from product where name like ?";
            s= (PreparedStatement) ConnectionDB.connect(sql);
            s.setString(1,"%"+txtSearch+ "%");
            ResultSet rs=s.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt(1));
                return rs.getInt(1);

            }



        }catch (SQLException | ClassNotFoundException throwbles){
            throwbles.printStackTrace();
        }
        return 0;

     }
     public List<Product> getProductWhereSearch(int index,int sizeData,String txtSearch){
        PreparedStatement s=null;
        try {
            List<Product>re=new LinkedList<>();
            String sql="select * from product where name like ? limit ?,?";
            s=ConnectionDB.connect(sql);
            s.setString(1,"%"+txtSearch+"%");
            s.setInt(2,index-1);
            s.setInt(3,sizeData);
            ResultSet rs=s.executeQuery();
            while(rs.next()){
                re.add(new Product(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getLong(4),
                        rs.getLong(5)));



            }
            rs.close();
            s.close();
            return re;
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return new LinkedList<>();
        }
     }

    public static void main(String[] args) {
        ProductEntity pe= new ProductEntity();
       pe.count("casio");
    }



}