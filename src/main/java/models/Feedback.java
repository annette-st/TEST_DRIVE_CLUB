package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Feedback {
    private String first_name;
    private String content;
}
