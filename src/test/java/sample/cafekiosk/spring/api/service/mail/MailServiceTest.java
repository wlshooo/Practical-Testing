package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //Mockito 사용해서 Mock 만들거야!
class MailServiceTest {

    @Mock
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;


    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        //given
        MailSendClient mailSendClient = mock(MailSendClient.class);
        MailSendHistoryRepository mailSendHistoryRepository = mock(MailSendHistoryRepository.class);

        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

        when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(true);

        //when
        boolean result = mailService.sendMail("", "", "", "");

        //then
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
        assertThat(result).isTrue();
    }

    @DisplayName("메일 전송 테스트 리팩토링 Mock")
    @Test
    void sendMailRefactoringMock() {
        //given
        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

        when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(true);

        //when
        boolean result = mailService.sendMail("", "", "", "");

        //then
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
        assertThat(result).isTrue();
    }

    @DisplayName("메일 전송 테스트 리팩토링 Mock + InjectMocks")
    @Test
    void sendMailRefactoringMockInjectMock() {
        //mailService의 생성자를 보고 @Mock으로 선언된 객체를 Inject해준다.

        //given
        when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(true);

        //when
        boolean result = mailService.sendMail("", "", "", "");

        //then
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
        assertThat(result).isTrue();
    }

    @DisplayName("메일 전송 테스트 With BDD Mockito")
    @Test
    void sendMailWithBDDMockito() {
        //given
        MailSendClient mailSendClient = mock(MailSendClient.class);
        MailSendHistoryRepository mailSendHistoryRepository = mock(MailSendHistoryRepository.class);

        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

        BDDMockito.given(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
                .willReturn(true);

        //when
        boolean result = mailService.sendMail("", "", "", "");

        //then
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
        assertThat(result).isTrue();
    }

}