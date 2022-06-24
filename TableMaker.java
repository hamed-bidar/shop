import java.sql.*;

public class TableMaker {


    Connection conn = null;

    public TableMaker(Connection con) {
        this.conn = con;
    }

    public void CreateTable() {


        System.out.println("HI");

         try {
             Statement statement = conn.createStatement();
             statement.executeUpdate("CREATE TABLE tbl_basket   (id serial NOT NULL PRIMARY KEY,title VARCHAR NOT NULL,FOREIGN KEY (product_id) REFERENCES tbl_basket (id))");
             statement.executeUpdate("CREATE TABLE tbl_product  (id serial NOT NULL PRIMARY KEY,title VARCHAR NOT NULL, FOREIGN KEY (cat_id) REFERENCES tbl_product (id))");
             statement.executeUpdate("CREATE TABLE tbl_cat      (id serial NOT NULL PRIMARY KEY,title VARCHAR NOT NULL)");

             System.out.println("Successfully created tables");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


}

