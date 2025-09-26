package com.vidhuras.hospital_app.Repository;

import com.vidhuras.hospital_app.Entity.Blog;
import com.vidhuras.hospital_app.Entity.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByStatus(String status);
}
