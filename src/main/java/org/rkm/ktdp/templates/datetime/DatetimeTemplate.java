package org.rkm.ktdp.templates.datetime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.templates.specification.FormattedTemplate;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;

@EqualsAndHashCode(callSuper = true)
public abstract class DatetimeTemplate extends FormattedTemplate {
    protected final DateTimeFormatter dateTimeFormatter;
    protected final ZoneId zoneId;

    protected DatetimeTemplate(@JsonProperty(value = "name") String name,
                               @JsonProperty(value = "format") String format,
                               @JsonProperty(value = "zoneId") String zoneId) {
        super(name, format);
        this.zoneId = ZoneId.of(zoneId);
        this.dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendPattern(format)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter()
                .withResolverStyle(ResolverStyle.SMART)
                .withZone(this.zoneId);
    }
}
