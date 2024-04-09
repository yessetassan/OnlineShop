package yesko.project.OnlineShop.dto;

import lombok.Getter;
import lombok.Setter;
import yesko.project.OnlineShop.entity.Discount;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import static yesko.project.OnlineShop.utils.Constants.formatter;
import static yesko.project.OnlineShop.utils.Constants.nowUtcPlusOne;

@Getter
@Setter
public class DiscountDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal discountPercent;
    private boolean active;
    private String createdAt;
    private String modifiedAt;

    public static DiscountDto fromEntity(Discount discount) {
        DiscountDto discountDto = new DiscountDto();
        discountDto.setId(discount.getId());
        discountDto.setName(discount.getName());
        discountDto.setDescription(discount.getDescription());
        discountDto.setDiscountPercent(discount.getDiscountPercent());
        discountDto.setActive(discount.getActive());
        Timestamp created = discount.getCreatedAt();
        Timestamp modified = discount.getModifiedAt();
        discountDto.setCreatedAt(formatter.format(created));
        discountDto.setModifiedAt(formatter.format(modified));
        return discountDto;
    }
}