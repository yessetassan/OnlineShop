package yesko.project.OnlineShop.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yesko.project.OnlineShop.auth.EmailRequest;
import yesko.project.OnlineShop.entity.User;
import yesko.project.OnlineShop.entity.UserAuth;
import yesko.project.OnlineShop.entity.UserInfo;
import yesko.project.OnlineShop.repo.UserAuthRepo;

import static yesko.project.OnlineShop.utils.Constants.*;
@Service
@AllArgsConstructor
public class EmailSenderService {

    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;
    private final UserInfoService userInfoService;
    private final UserAuthRepo userAuthRepo;

    public String sendMail(EmailRequest emailDto) {
        UserInfo existingUserInfo = userInfoService.findByEmail(emailDto.getEmail())
                .orElseThrow(() -> new IllegalStateException("Cannot update userInfo. UserInfo with email " + emailDto.getEmail() + " does not exist."));
        User user = existingUserInfo.getUser_userInfo();
        String toEmail = emailDto.getEmail(),
                generateSecretCode = RandomStringUtils
                        .random(EIGHT_SIZED_SECRET_CODE, USE_LETTERS_IN_SECRET_CODE, USE_NUMBERS_IN_SECRET_CODE),
                body = String.format("Dear %s %s,\n" +
                                "\n" +
                                "We've received a request to reset the password for your account. To ensure the security of your information, please use the password provided below to access your account and promptly set a new password.\n" +
                                "\n" +
                                "Temporary Password: %s",
                        user.getLastName(), user.getFirstName(), generateSecretCode),
                subject = String.format("Immediate Action Required: Password Reset for %s, %s",
                        user.getLastName(), user.getFirstName());


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("yesset.assan@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);

        UserAuth userAuth = user.getUserAuth();
        userAuth.setPassword(passwordEncoder.encode(generateSecretCode));
        userAuthRepo.save(userAuth);

        userInfoService.save(existingUserInfo);
        return "Mail Sent Successfully...";
    }
}

