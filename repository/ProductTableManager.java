package repository;

import shop.Category;
import shop.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductTableManager {

    Connection conn = null;

    public ProductTableManager(Connection conn) {
        this.conn = conn;
    }

    public long insertProduct(Product product ){

        long dbId=0;
        String SQL = "INSERT INTO tbl_product(title,category_id) "
                + "VALUES(?,?)";
        try{
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1,product.getTitle());
            pstmt.setDouble(2,product.getCategory().getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        dbId = rs.getLong("id");
                        System.out.println("Product " + rs.getString("title") + " created.");

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

    public List<Product> getAllProducts(){
        List<Product> products=new ArrayList<>();
        //String SQL = "SELECT  p.id , p.title  , c.id as category_id, c.title as category_title " +
        //        "from tbl_product as p JOIN tbl_category as c on p.category_id=c.id";
        String SQL = "SELECT * FROM tbl_product";
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
                 long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                long catId =resultSet.getLong("category_id");
                String categoryTitle = resultSet.getString("category_title");


                Category category = new Category();
                category.setId(catId);
                category.setTitle(categoryTitle);

                Product product=new Product();
                product.setId(id);
                product.setTitle(title);
                product.setCategory(category);

                products.add(product);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

}
