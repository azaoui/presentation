package com.azaoui.hibernate1.controller;

import java.util.List;

import com.azaoui.hibernate1.model.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azaoui.hibernate1.repository.ContactRepository;

/* the rest controller contains the @controller and @repositoyBody annotation*/
/*the request mapping indicates that the url of the api in the controller will start with /contact */
@RestController
@RequestMapping({ "/contacts" })
public class ContactController {

  private ContactRepository contactRepository;

  ContactController(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;

  }

  /*
   * the findAmm method is going to retrieve all records from the database select
   * * from contact
   */
  @GetMapping
  public List findAll() {
    return contactRepository.findAll();
  }

  /* retrieve a contact by id get */
  /**/
  @GetMapping(path = { "/{id}" })
  public ResponseEntity<Contact> findById(@PathVariable long id) {
    return contactRepository.findById(id)
                            .map(record -> ResponseEntity.ok().body(record))
                            .orElse(ResponseEntity.notFound().build());
    /*
     * retrieve from database where id , if a contact found we return 200 ok , else
     * we return 404 not found
     */

  }
}
