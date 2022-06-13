package com.example.book.repository;

import com.example.book.entity.Books;
import com.example.book.entity.enums.Tili;
import com.sun.xml.bind.v2.schemagen.episode.Klass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Books,Integer> {

    Optional<Books> getByTili(Tili tilli);




    Optional<Books> getByBookname(String bookname);


     Optional<Books> getByBooknameAndAuthor(String bookname,String author);

//
//    List < Employee > findByEmployeeNames ( @Param ( "names" ) List < String > names ) ;
//    @Query ( nativeQuery = true ,value = "SELECT * FROM Employee as e WHERE e.employeeName IN (:names)" )


//    @Query("SELECT u FROM User u WHERE u.status = ?1")
//    User findUserByStatus(Integer status);


//    @Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2")
//    User findUserByStatusAndName(Integer status, String name);

//    @Modifying
//    @Query("update User u set u.status = :status where u.name = :name")
//    int updateUserSetStatusForName(@Param("status") Integer status,
//                                   @Param("name") String name);


//    @Modifying
//    @Query(
//            value =
//                    "insert into Users (name, age, email, status) values (:name, :age, :email, :status)",
//            nativeQuery = true)
//    void insertUser(@Param("name") String name, @Param("age") Integer age,
//                    @Param("status") Integer status, @Param("email") String email);


//    SELECT u FROM User u WHERE u.email LIKE '%email1%'
//    or  u.email LIKE '%email2%'
//            ...
//    or  u.email LIKE '%emailn%'
}
