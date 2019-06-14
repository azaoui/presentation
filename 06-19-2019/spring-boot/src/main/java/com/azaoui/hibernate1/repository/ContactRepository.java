package com.azaoui.hibernate1.repository;

import com.azaoui.hibernate1.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* to easy access the methods to manipulate the contact table,
 we just need to create an interface that extends jpaRepository
  passing the class that represents our model and tge type of primary key as generic arguments*/

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
