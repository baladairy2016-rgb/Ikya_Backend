package com.vidhuras.hospital_app.Service;

import com.vidhuras.hospital_app.Dto.BlogRequestDto;
import com.vidhuras.hospital_app.Dto.BlogResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BlogService {


    List<BlogResponseDto> getAllBlogs();

    BlogResponseDto getBlogById(Long id);

    void deleteBlog(Long id);

    BlogResponseDto addBlog(BlogRequestDto dto, String createdBy, MultipartFile imageFile, MultipartFile iconFile) throws IOException;

    BlogResponseDto updateBlog(Long id, BlogRequestDto dto, String modifiedBy, MultipartFile imageFile, MultipartFile iconFile) throws IOException;
}
