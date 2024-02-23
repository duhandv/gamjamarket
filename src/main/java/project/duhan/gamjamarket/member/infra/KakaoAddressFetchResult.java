package project.duhan.gamjamarket.member.infra;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.duhan.gamjamarket.member.domain.Address;

@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = SnakeCaseStrategy.class)
@Getter
public class KakaoAddressFetchResult {

    private List<Document> documents;

    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(value = SnakeCaseStrategy.class)
    @Getter
    static class Document {

        private String regionType;

        private String addressName;

        @JsonProperty("region_1depth_name")
        private String region1depthName;

        @JsonProperty("region_2depth_name")
        private String region2depthName;

        @JsonProperty("region_3depth_name")
        private String region3depthName;

    }

    public Address toAddress() {
        Document document = documents.stream()
            .filter(res -> res.regionType.equals(RegionType.HAENGJEONGDONG.code))
            .findFirst()
            .orElseThrow();
        return new Address(document.region1depthName, document.region2depthName, document.region3depthName);
    }

    @Getter
    public enum RegionType {

        HAENGJEONGDONG("H"), BEOBJEONGDONG("B");

        private final String code;

        RegionType(String code) {
            this.code = code;
        }

    }

}
