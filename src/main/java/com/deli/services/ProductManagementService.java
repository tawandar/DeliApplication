package com.deli.services;

import com.deli.config.PersistenceUnit;
import com.deli.entities.Product;
import com.deli.models.Database;
import org.springframework.stereotype.Service;

//package com.deli.service;
//
//import com.deli.config.PersistenceUnit;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Query;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
//import java.util.Date;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;

@Service
public class ProductManagementService {


    PersistenceUnit pu = PersistenceUnit.getInstance();
    private final EntityManagerFactory emf = pu.emFactory;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Product> getAllProducts() {
        List<Product> result =new ArrayList<>();
        LocalDateTime ldt = LocalDateTime.now();
        LocalDate ld = ldt.toLocalDate();
        Date d = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date t = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

        try {

            EntityManager em = getEntityManager();
            Query query1 = em.createQuery("SELECT p FROM Product p");
            result=(List<Product>) query1.getResultList();

        } catch (Exception ex) {
            Logger.getLogger(com.deli.services.ProductManagementService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            emf.close();

            if(!emf.isOpen()){

                System.out.println(" Pesisternce connection closed ");
            }

        }

        return result;
    }


    public List<Product> getProducts() {
        List<Product> result =new ArrayList<>();

        Database database=new Database();

        try {
            database.connect_db();
            String query = "SELECT * FROM Product";
            ResultSet resultSet;

            resultSet=database.select_db(query);

            while(resultSet.next()){

                Product product=new Product(Integer.parseInt(resultSet.getString("productId")), Float.parseFloat(resultSet.getString("price")), resultSet.getString("name"), Integer.parseInt(resultSet.getString("quantity")));

                result.add(product);

            }


        }

        catch(Exception ex){
            ex.printStackTrace();
        }

        return result;

    }
}
