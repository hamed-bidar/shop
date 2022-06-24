package repository;
import shop.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatTableManager {
    private Connection conn = null;

    public CatTableManager(Connection conn) {
        this.conn = conn;
    }


    public long insertCategory(Category category) {
        long dbId = 0;
        String SQL = "INSERT INTO tbl_category(title)" +
                "VALUES(?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, category.getTitle());

            int affected = pstm.executeUpdate();
            if (affected > 0) {
                try (ResultSet rs = pstm.getGeneratedKeys()) {

                    if (rs.next()) {
                        int id = rs.getInt("id");
                        System.out.printf("id ");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dbId;
    }


    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String SQL = "SELECT  * from tbl_category ";
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

                Category category = new Category();
                category.setId(id);
                category.setTitle(title);

                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}


