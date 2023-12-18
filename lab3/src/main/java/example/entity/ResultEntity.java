package example.entity;

import example.utils.ZonedDateTimeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * Represents a result entity for storing point data.
 * This entity is mapped to a database table 'lab3_x_test_table' within the schema 'sXXXXXX'.
 * IT SHOULD INCLUDE (in theory) information about the point coordinates (x, y), radius (r) and whether the point is within a certain area (result).
 */
@Entity
@Table(name = "lab3_x_test_table", schema = "s367813")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "XValue")
    private double x;
    @Column(name = "YValue")
    private double y;
    @Column(name = "RValue")
    private double r;
    @Column(name = "Result")
    private boolean success;
    @Column(name = "Watch")
    @Convert(converter = example.utils.ZonedDateTimeConverter.class)
    private ZonedDateTime watch;
    @Column(name = "duration")
    private double duration;
    @Column(name = "SessionID")
    private String sessionId;

}
