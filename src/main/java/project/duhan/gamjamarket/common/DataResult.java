package project.duhan.gamjamarket.common;

import lombok.Getter;

@Getter
public class DataResult<T> {

    private final T data;

    private DataResult(T data) {
        this.data = data;
    }

    public static <T> DataResult<T> of(T data) {
        return new DataResult<>(data);
    }

}
