package yesko.project.OnlineShop.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import yesko.project.OnlineShop.entity.User;

import java.time.LocalDateTime;


@Component
public class UserSpecifications {

    public static Specification<User> hasUsername(String username) {
        return (root, query, cb) -> {
            if (username == null) {
                return null;
            }
            return cb.equal(root.get("username"), username);
        };
    }

    public static Specification<User> hasFirstName(String firstName) {
        return (root, query, cb) -> {
            if (firstName == null) {
                return null;
            }
            return cb.equal(root.get("firstName"), firstName);
        };
    }

    public static Specification<User> hasLastName(String lastName) {
        return (root, query, cb) -> {
            if (lastName == null) {
                return null;
            }
            return cb.equal(root.get("lastName"), lastName);
        };
    }

    public static Specification<User> createdAfter(LocalDateTime date) {
        return (root, query, cb) -> {
            if (date == null) {
                return null;
            }
            return cb.greaterThanOrEqualTo(root.get("createdAt"), date);
        };
    }
}

