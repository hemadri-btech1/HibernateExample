package com.boraji.tutorial.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.boraji.tutorial.hibernate.entity.Department;
import com.boraji.tutorial.hibernate.entity.Employee;

/**
 * @author imssbora
 */
public class MainApp {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.getTransaction();
         transaction.begin();

         Department department = new Department();
         department.setName("IT Department1");

         Employee employee1 = new Employee();
         employee1.setName("Adam1");
         employee1.setDesignation("Manager1");
         employee1.setDepartment(department);

         Employee employee2 = new Employee();
         employee2.setName("Miller1");
         employee2.setDesignation("Software Engineer1");
         employee2.setDepartment(department);

         Employee employee3 = new Employee();
         employee3.setName("Smith1");
         employee3.setDesignation("Associate  Engineer1");
         employee3.setDepartment(department);

         department.getEmployees().add(employee1);
         department.getEmployees().add(employee2);
         department.getEmployees().add(employee3);

         session.persist(department);

         transaction.commit();
      } catch (Exception e) {
         if (transaction != null) {
            transaction.rollback();
         }
         e.printStackTrace();
      } finally {
         if (session != null) {
            session.close();
         }
      }

      HibernateUtil.shutdown();
   }
}
