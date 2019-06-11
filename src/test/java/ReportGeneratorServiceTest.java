import logic.IReportGenerator;
import logic.ReportEntity;
import logic.ReportGeneratorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class ReportGeneratorServiceTest {

  @InjectMocks
  private ReportGeneratorService reportGeneratorService;
  @Mock
  private IReportGenerator reportGenerator;
  @Captor
  private ArgumentCaptor<ReportEntity> reportCaptor;
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }
  @SuppressWarnings("deprecation")
  @Test
  public void test() {
    Calendar startDate = Calendar.getInstance();
    startDate.set(2016, 11, 25);
    Calendar endDate = Calendar.getInstance();
    endDate.set(9999, 12, 31);
    String reportContent = "Report Content";
    reportGeneratorService.generateReport(startDate.getTime(), endDate.getTime(), reportContent.getBytes());
    Mockito.verify(reportGenerator).generateReport(reportCaptor.capture());
    ReportEntity report = reportCaptor.getValue();
    assertEquals(116, report.getStartDate().getYear());
    assertEquals(11, report.getStartDate().getMonth());
    assertEquals(25, report.getStartDate().getDate());
    assertEquals(8100, report.getEndDate().getYear());
    assertEquals(0, report.getEndDate().getMonth());
    assertEquals(31, report.getEndDate().getDate());
    assertEquals("Report Content", new String(report.getContent()));
  }
}
