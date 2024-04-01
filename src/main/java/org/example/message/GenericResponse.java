package org.example.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.domain.Project;
import org.example.repository.ProjectRepository;

@Data
@Builder
@AllArgsConstructor
public class GenericResponse <T>{
    private boolean success;
    private String message;
    private T data;

    public static <T> GenericResponse<T> empty() {
        return success(null);
    }

    public static <T> GenericResponse<T> success(T data) {
        return GenericResponse.<T>builder()
                .message("SUCCESS!")
                .data(data)
                .success(true)
                .build();
    }

    public static <T> GenericResponse<T> error() {
        return GenericResponse.<T>builder()
                .message("ERROR!")
                .success(false)
                .build();
    }


     //   public static final String PROJECT_CREATED_MESSAGE = "Yeni proje oluşturuldu: ";

    }

