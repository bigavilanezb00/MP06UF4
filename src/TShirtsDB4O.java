import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import Entities.Article;
import Entities.CreditCard;
import Entities.Customer;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import Entities.Order;

/**
 * @author Joan Anton Perez Branya
 * @since 19/02/2017
 *
 */

public class TShirtsDB4O {
    public static ArrayList<Order> orders;
    static ObjectContainer db;


    /**
     * Implement TODO methods and run to test
     *
     * @param args
     *            no args
     * @throws IOException
     *             in order to read files
     * @throws ParseException
     *             in order to parse data formats
     */
    public static void main(String[] args) throws IOException, ParseException {
        TShirtsDB4O TSM = new TShirtsDB4O();
        FileAccessor fa = new FileAccessor();
        fa.readArticlesFile("articles.csv");
        fa.readCreditCardsFile("creditCards.csv");
        fa.readCustomersFile("customers.csv");
        fa.readOrdersFile("orders.csv");
        fa.readOrderDetailsFile("orderDetails.csv");
        orders = fa.orders;
        try {

            File file = new File("orders.db");
            String fullPath = file.getAbsolutePath();
            db = Db4o.openFile(fullPath);

            TSM.addOrders();
            TSM.listOrders();
            TSM.listArticles();
            TSM.addArticle(7, "CALCETINES EJECUTIVOS 'JACKSON 3PK'", "gris", "45", 18.00);
            TSM.updatePriceArticle(7, 12.00);
            TSM.llistaArticlesByName("CALCETINES EJECUTIVOS 'JACKSON 3PK'");
            TSM.deletingArticlesByName("POLO BÁSICO 'MANIA'");
            TSM.deleteArticleById(7);
            TSM.listArticles();
            TSM.listCustomers();
            TSM.changeCreditCardToCustomer(1);
            TSM.listCustomers();
            TSM.llistaCustomerByName("Laura");
            TSM.showOrdersByCustomerName("Laura");
            TSM.showCreditCardByCustomerName("Laura");
            TSM.deleteCustomerbyId(2);
            TSM.retrieveOrderContentById_Order(2);
            TSM.deleteOrderContentById_Order(2);
            TSM.retrieveOrderContentById_Order(2);
            TSM.listCustomers();
            TSM.clearDatabase();
            TSM.listOrders();

        } finally {
            // close database
            db.close();
        }
    }



    public void changeCreditCardToCustomer(int i) {
        // TODO Auto-generated method stub

    }

    public void updatePriceArticle(int id, double newPrice) {
        // TODO Auto-generated method stub

    }

    public void addArticle(int i, String string, String string2, String string3, double d) {
        // TODO Auto-generated method stub



    }

    public void deleteArticleById(int i) {
        // TODO Auto-generated method stub

    }

    public void deleteOrderContentById_Order(int i) {
        // TODO Auto-generated method stub

    }


    public void retrieveOrderContentById_Order(int i) {
        // TODO Auto-generated method stub

    }


    public void deleteCustomerbyId(int i) {
        // TODO Auto-generated method stub

    }



    public void showCreditCardByCustomerName(String string) {
        List<CreditCard> creditCards = db.queryByExample(new Predicate<CreditCard>() {
            public boolean match(CreditCard creditCard)
        })
    }


    public void showOrdersByCustomerName(String string) {


    }

    public void clearDatabase() {
    }


    public void deletingArticlesByName(String string) {
        System.out.println("Borrando Articulos");


    }

    public void llistaArticlesByName(String string) {
        System.out.println("Mirando la base de datos");
        ObjectSet<Article> resultado = db.queryByExample(new Article());
        for (Article article : resultado) {
            if (article.getName().equals(string));
        }
    }


    public void llistaCustomerByName(String string) {

        System.out.println("Mirando base de datos por nombre");
        ObjectSet<Customer> resultado = db.queryByExample(new Customer());
        for (Customer customer : resultado){
            if (customer.getName().equals(string)){
                System.out.println(customer.toString());
            }
        }

    }


    public void listCustomers() {

        System.out.println("Mirando base de datos");
        ObjectSet<Customer> resultado = db.queryByExample(new Customer());
        while (resultado.hasNext()){
            System.out.println(resultado.next());
        }
    }


    public void listArticles() {

        System.out.println("Mirando base de datos");
        ObjectSet<Article> resultado = db.queryByExample(new Article());
        while (resultado.hasNext()){
            System.out.println(resultado.next());
        }

    }

    public void addOrders() {

        System.out.println("Agregar Orders a la base de datos: ");
        for(int i = 0; i< orders.size(); i++)
        {
            System.out.println(orders.get(i).toString());
            db.store(orders.get(i));
        }

    }

    public void listOrders() {

        System.out.println("Mirando base de datos");
        ObjectSet<Order> resultado = db.queryByExample(new Order());
        while (resultado.hasNext()){
            System.out.println(resultado.next());
        }
    }
}
