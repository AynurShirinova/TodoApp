
package org.example.domain;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@SuppressWarnings("ALL")
@Getter
@Setter
public class Project {
    private UUID id;
    private String description;
    private String title;
   private String createdAt;
@Builder
    public Project( UUID id,  String title,String description,String createdAt) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.createdAt=createdAt;
        this.description=description;
    }

}
