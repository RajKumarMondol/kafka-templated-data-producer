package org.rkm.ktdp.templates.specification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.rkm.ktdp.templates.characters.RandomString;
import org.rkm.ktdp.templates.characters.UUIDString;
import org.rkm.ktdp.templates.date.CurrentDate;
import org.rkm.ktdp.templates.date.DateSequence;
import org.rkm.ktdp.templates.date.RandomDate;
import org.rkm.ktdp.templates.datetime.CurrentDateTime;
import org.rkm.ktdp.templates.datetime.DateTimeSequence;
import org.rkm.ktdp.templates.datetime.RandomDateTime;
import org.rkm.ktdp.templates.fromsource.RandomFromSource;
import org.rkm.ktdp.templates.fromsource.SequenceFromSource;
import org.rkm.ktdp.templates.number.DoubleSequence;
import org.rkm.ktdp.templates.number.RandomDouble;
import org.rkm.ktdp.templates.time.CurrentTime;
import org.rkm.ktdp.templates.time.RandomTime;
import org.rkm.ktdp.templates.time.TimeSequence;
import org.rkm.ktdp.templates.wholenumber.IntegerSequence;
import org.rkm.ktdp.templates.wholenumber.RandomInteger;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RandomDate.class),
        @JsonSubTypes.Type(value = DateSequence.class),
        @JsonSubTypes.Type(value = CurrentDate.class),
        @JsonSubTypes.Type(value = RandomString.class),
        @JsonSubTypes.Type(value = UUIDString.class),
        @JsonSubTypes.Type(value = CurrentDateTime.class),
        @JsonSubTypes.Type(value = RandomDateTime.class),
        @JsonSubTypes.Type(value = DateTimeSequence.class),
        @JsonSubTypes.Type(value = RandomFromSource.class),
        @JsonSubTypes.Type(value = SequenceFromSource.class),
        @JsonSubTypes.Type(value = RandomDouble.class),
        @JsonSubTypes.Type(value = DoubleSequence.class),
        @JsonSubTypes.Type(value = RandomTime.class),
        @JsonSubTypes.Type(value = TimeSequence.class),
        @JsonSubTypes.Type(value = CurrentTime.class),
        @JsonSubTypes.Type(value = RandomInteger.class),
        @JsonSubTypes.Type(value = IntegerSequence.class),
})
@EqualsAndHashCode
@ToString
public abstract class BaseTemplate {
    @Getter
    private final String name;

    public BaseTemplate(@JsonProperty(value = "name") String name) {
        this.name = name;
    }

    public abstract String generate();
}
