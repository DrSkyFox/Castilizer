package oba.castilizer.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ChangeSet {
    private String paramName;
    private String paramSet;

}
