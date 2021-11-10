package mangpo.server.entity;

import lombok.*;
import mangpo.server.entity.common.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//noargs accesslevel protected 로 변경하고 allargs 때서 리팩토링
public class Book extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_info_id")
    private BookInfo bookInfo;

    @Column(name = "book_name")
    private String name;

    @Column(name = "book_isbn")
    private String isbn;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_category")
    private BookCategory category;

    @Builder.Default
    @OneToMany(mappedBy = "book")
    private List<Post> posts = new ArrayList<>();

    public void update(Book bookRequest){
        this.name = bookRequest.name;
        this.isbn = bookRequest.isbn;
        this.category = bookRequest.category;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }
}
