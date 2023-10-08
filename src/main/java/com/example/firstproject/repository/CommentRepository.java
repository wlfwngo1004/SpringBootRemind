package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> { //JpaRepository<대상엔티티, 대표키타입>
    // *쿼리를 메소드로 작상하기!(네이트브 쿼리 메소드) -> @Query 어노테이션 사용 or orm.xml파일 이용!*
    // 1. 특정 게시글의 모든 댓글 조회(@Query 어노테이션 사용해보기)
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId",
            nativeQuery = true)
    List<Comment> findByArticleId(Long articleId); // 메소드 실행 결과로 댓글의 묶음을 반환하기때문에 List<Comment>로 반환형!
    // 2. 특정 닉네임의 모든 댓글 조회(orm.xml 파일 이용해보기)
    List<Comment> findByNickname(String nickname);
}
