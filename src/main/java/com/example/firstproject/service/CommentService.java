package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        /*// 1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 2. 엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for(int i=0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }*/

        // 3. 결과 반환
        return commentRepository.findByArticleId(articleId)// 위 for문을 stream 사용하여 코드 가독성을 높인다. 조금 더 공부 필요. 컬렉션 및 람다식 확인하자.
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList()); // .map은 Stream<Object>를 반환! List로 반환받기 위해서 collect메소드 추가.
    }

    @Transactional // 생성은 DB내용을 변경하는 작업이기때문에 트랜잭션 필요!
    public CommentDto create(Long articleId, CommentDto dto) {
        // 1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! " +
                "대상 게시글이 없습니다.")); // orElseThrow() 메소드는 Optional객체에 값이 있으면 그 값을 반환하고 값이 없으면 전달값으로 보낸 예외를 발생시킴!
        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        // 3. 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);
        // 4. DTO로 변환해 반환
        return CommentDto.createCommentDto(created);// 컨트롤러에서 DTO 타입으로 반환받는다 초기화해놨음!
    }
}
