package shop;

public class Basket {

    private long id;
    private String title;
    private Product  products;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Product  getProducts() {
        return products;
    }

    public void setProducts(Product  products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", products=" + products  +
                '}';
    }

}
