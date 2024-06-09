package com.urfu.testing.extern.repository;

import com.urfu.testing.domain.Head;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HeadRepository extends CrudRepository<Head, UUID> {

}
