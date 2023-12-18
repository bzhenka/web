package example.utils;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime attribute) {
        return (attribute == null ? null : Timestamp.valueOf(attribute.toLocalDateTime()));
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp dbData) {
        return (dbData == null ? null : ZonedDateTime.ofInstant(dbData.toInstant(), ZoneId.systemDefault()));
    }
}