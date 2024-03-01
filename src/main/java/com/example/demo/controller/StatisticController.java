import com.example.demo.service.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticController {

    private static final Logger logger = LoggerFactory.getLogger(StatisticController.class);
    private final StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/statistics/by-date")
    public ResponseEntity<String> getStatisticsByDate(@RequestParam("date") String date) {
        logger.info("Received request for statistics by date: {}", date);
        String response = statisticService.getStatisticsByDate(date);
        return ResponseEntity.ok(response); // Возвращает HTTP 200 с телом ответа
    }

    @GetMapping("/statistics/by-asin")
    public ResponseEntity<String> getStatisticsByAsin(@RequestParam("asin") String asin) {
        logger.info("Received request for statistics by ASIN: {}", asin);
        String response = statisticService.getStatisticsByAsin(asin);
        return ResponseEntity.ok(response); // Возвращает HTTP 200 с телом ответа
    }

    // Другие методы для вывода статистики по другим параметрам
}
