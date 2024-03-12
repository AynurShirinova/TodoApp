
package org.example.domain;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@SuppressWarnings("ALL")
@Getter
@Setter
@Builder
public class Project {
    private UUID id;
    private String description;
    private String title;
   private String createdAt;


}
