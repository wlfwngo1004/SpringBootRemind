package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // DB가 자동으로 1씩 증가!
    private Long id;
    @ManyToOne // Article 엔티티와 다대일 관계.
    @JoinColumn(name="article_id") // 외래키 매핑(외래키 이름 / Article 엔티티의 id를 외래키로 지정할것이니까 article_id로!
    private Article article; // 해당 댓글의 부모 게시글.
    @Column
    private String nickname;
    @Column
    private String body;
}
