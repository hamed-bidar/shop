package repository;

import shop.Basket;
import shop.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasketTableManager {
    Connection conn = null;

    public BasketTableManager(Connection conn) {
        this.conn = conn;
    }
    public long insert(Basket basket){

        long dbId=0;
        String SQL = "INSERT INTO tbl_basket(title,product_id) "
                + "VALUES(?,?)";
        try{
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,basket.getTitle());
            pstmt.setDouble(2,basket.getProducts().getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                 try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        dbId = rs.getLong("id");
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dbId;
    }


    public List<Basket> getAllBaskets(){
        List<Basket> baskets=new ArrayList<>();
        //String SQL = "SELECT  b.id , b.title  , p.id as product_id, p.title as produc_title " +
          //      "from tbl_basket as b JOIN tbl_product as p on b.product=p.id";
        String SQL = "SELECT * FROM tbl_basket";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                //Display values
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                long prodId = resultSet.getLong("product_id");
                String categoryTitle = resultSet.getString("product_title");

                Product product=new Product();
                product.setId(prodId);
                product.setTitle(categoryTitle);

                Basket basket = new Basket();
                basket.setId(id);
                basket.setTitle(title);
                basket.setProducts(product);

                baskets.add(basket);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return baskets;
    }



}
