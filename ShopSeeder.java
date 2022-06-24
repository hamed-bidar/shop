import repository.BasketTableManager;
import repository.CatTableManager;
import repository.ProductTableManager;
import shop.Basket;
import shop.Category;
import shop.Product;

import java.sql.Connection;
import java.util.List;

public class ShopSeeder {


    public static void main(String[] args) {

        ConnectionManager cm = new ConnectionManager();
        Connection connection = cm.connect();



        CatTableManager catTableManager = new CatTableManager(connection);
        Category category = new Category();
        category.setTitle("cat_2");
        long categoryId = catTableManager.insertCategory(category);
        category.setId(categoryId);

        List<Category> categoryList = catTableManager.getAllCategories();
        System.out.println("category list:"+categoryList);

        ProductTableManager productTableManager = new ProductTableManager(connection);
        Product product = new Product();
        product.setTitle("product_2");
        product.setCategory(category);

        long prodId = productTableManager.insertProduct(product);
        product.setId(prodId);

        List<Product> productList = productTableManager.getAllProducts();
        System.out.println("productList"+productList);

        // create basket
        BasketTableManager basketTableManager = new BasketTableManager(connection);
        Basket basket = new Basket();
        basket.setTitle("basket_1");
        basket.setProducts(product);
        long basketId = basketTableManager.insert(basket);
        basket.setId(basketId);

        List<Basket> basketList= basketTableManager.getAllBaskets();
        System.out.println("basketList"+basketList);





    }


}
