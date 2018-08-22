package net.palm7.raa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import net.palm7.raa.util.Exclude;

@Exclude
public interface BaseRepository<T> extends CrudRepository<T, Long>, JpaRepository<T, Long> {
}
