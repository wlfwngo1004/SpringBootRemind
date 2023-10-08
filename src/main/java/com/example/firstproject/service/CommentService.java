package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
