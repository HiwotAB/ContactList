package com.hiwotab.contactapp.repository;

import com.hiwotab.contactapp.model.Contact;
import org.springframework.data.repository.CrudRepository;


public interface ContactRepository extends CrudRepository<Contact, Long> {
}