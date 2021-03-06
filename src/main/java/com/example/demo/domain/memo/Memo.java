package com.example.demo.domain.memo;

import com.example.demo.domain.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Setter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Memo extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    private LocalDateTime createDate;
    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

    public Memo(String title, String username, String contents) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }

    public Memo(MemoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();
    }

    public void update(MemoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();
    }
}
