package corea.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Room {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    private long memberId;

    @Column(length = 25000)
    private String repositoryLink;

    @Column(length = 25000)
    private String thumbnailLink;

    private int matchingSize;

    private String keyword;

    private LocalDateTime submissionDeadline;

    private LocalDateTime reviewDeadline;

    public Room(final String title,
                final long memberId,
                final String repositoryLink,
                final String thumbnailLink,
                final int matchingSize,
                final String keyword,
                final LocalDateTime submissionDeadline,
                final LocalDateTime reviewDeadline) {
        this(null, title, memberId, repositoryLink, thumbnailLink, matchingSize, keyword, submissionDeadline, reviewDeadline);
    }
}

