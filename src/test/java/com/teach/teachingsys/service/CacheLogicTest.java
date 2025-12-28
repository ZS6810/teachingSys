package com.teach.teachingsys.service;

import com.teach.teachingsys.entity.Course;
import com.teach.teachingsys.repository.CourseRepository;
import com.teach.teachingsys.repository.UserRepository;
import com.teach.teachingsys.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
public class CacheLogicTest {

    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;
    
    @MockBean
    private UserRepository userRepository;

    @TestConfiguration
    @EnableCaching
    static class TestCacheConfig {
        @Bean
        @Primary
        public CacheManager cacheManager() {
            // Use in-memory cache manager to verify annotations without Redis
            return new ConcurrentMapCacheManager("course", "user", "questionBank", "exam", "system");
        }
    }

    @Test
    public void testCourseCache() {
        Long courseId = 1L;
        Course course = new Course();
        course.setId(courseId);
        course.setCourseName("Test Course");

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        // 1. First call: should hit DB (Repository)
        System.out.println("First call...");
        courseService.findById(courseId);
        verify(courseRepository, times(1)).findById(courseId);

        // 2. Second call: should hit Cache (Repository NOT called again)
        System.out.println("Second call...");
        courseService.findById(courseId);
        verify(courseRepository, times(1)).findById(courseId); 

        // 3. Evict (delete)
        System.out.println("Deleting...");
        courseService.deleteById(courseId);
        
        // 4. Third call: should hit DB again
        System.out.println("Third call...");
        courseService.findById(courseId);
        verify(courseRepository, times(2)).findById(courseId);
    }
}
